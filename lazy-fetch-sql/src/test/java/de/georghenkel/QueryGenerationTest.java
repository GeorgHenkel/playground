package de.georghenkel;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.hibernate.hql.spi.QueryTranslatorFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.georghenkel.model.EntityRelation;

@RunWith(Arquillian.class)
public class QueryGenerationTest {

	@PersistenceContext
	EntityManager em;

	@Deployment
	public static Archive<?> deploy() {
		return ShrinkWrap.create(WebArchive.class, "lazy-fetch-sql.war").addPackage(EntityRelation.class.getPackage())
				.addAsResource("META-INF/persistence.xml").addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void emptyInContainerTest() {
		String queryString1 = getSql(
				"select e from EntityWithRelation e left join e.entityRelation er where er.id = :id");
		String queryString2 = getSql("select e from EntityWithoutRelation e where e.entityRelationId = :id");

		System.out.println(queryString1);
		System.out.println(queryString2);

		Assert.assertEquals(queryString1, queryString2);
	}

	private String getSql(String query) {
		QueryTranslatorFactory queryTranslatorFactory = new ASTQueryTranslatorFactory();
		SessionImplementor hibernateSession = em.unwrap(SessionImplementor.class);
		QueryTranslator queryTranslator = queryTranslatorFactory.createFilterTranslator("", query,
				Collections.EMPTY_MAP, hibernateSession.getFactory());
		queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);

		return queryTranslator.getSQLString();
	}
}