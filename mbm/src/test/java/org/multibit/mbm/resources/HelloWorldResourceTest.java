package org.multibit.mbm.resources;

import org.junit.Test;
import org.multibit.mbm.core.Saying;
import org.multibit.mbm.test.BaseJerseyResourceTest;

import static org.junit.Assert.assertEquals;

/**
 * <p>[Pattern] to provide the following to {@link Object}:</p>
 * <ul>
 * <li></li>
 * </ul>
 * <p>Example:</p>
 * <pre>
 * </pre>
 *
 * @since 0.0.1
 *         
 */
public class HelloWorldResourceTest extends BaseJerseyResourceTest {


  @Override
  protected void setUpResources() {
    addResource(new HelloWorldResource("Hello, %s!","Stranger"));

    setUpAuthenticator();
  }

  @Test
  public void simpleResourceTest() throws Exception {

    Saying expectedSaying = new Saying(1,"Hello, Stranger!");

    Saying actualSaying = client()
      .resource("/hello-world")
      .get(Saying.class);

    assertEquals("GET hello-world returns a default",expectedSaying.getContent(),actualSaying.getContent());

  }


  @Test
  public void hmacResourceTest() throws Exception {

    Saying actual = client()
      .resource("/secret")
      .get(Saying.class);

    assertEquals("GET secret returns unauthorized","You cracked the code!", actual.getContent());

  }

}