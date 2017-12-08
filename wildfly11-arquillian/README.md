Embedded Arquillian WildFly 11 Example
===

I have tried to setup an embedded WildFly 11 Arquillian test case. I have found the nice sample project with an embedded WildFly 10 configuration for arquillian by MatousJobanek here: 
<https://github.com/MatousJobanek/examples/tree/master/wildfly-arquillian/wildfly-arquillian-embedded-example>

Problem
===

On my workstation there was already running a service on localhost:8080, so I needed a way to define another port. To get it to work I had to add 

     <argLine>-Djboss.socket.binding.port-offset=100</argLine>
     
to the configuration of the Surefire plugin in `pom.xml`.

And I had to set the changed managemement port in the `arquillian.xml` in **src/test/resources**:

    <property name="managementPort">10090</property>  