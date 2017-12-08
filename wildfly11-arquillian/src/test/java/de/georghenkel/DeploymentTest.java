package de.georghenkel;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DeploymentTest {

	@Deployment
	public static Archive<WebArchive> deploy() {
		return ShrinkWrap.create(WebArchive.class, "wildfly11-arquillian.war");
	}

	@Test
	public void emptyInContainerTest() {
		System.out.println("=========================================");
		System.out.println("This test should run inside the container");
		System.out.println("=========================================");
	}
}