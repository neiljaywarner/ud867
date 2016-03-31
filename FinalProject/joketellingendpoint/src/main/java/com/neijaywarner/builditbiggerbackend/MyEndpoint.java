/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.neijaywarner.builditbiggerbackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.neiljaywarner.builditbigger.JokeFactory;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "builditbiggerbackend.neijaywarner.com",
    ownerName = "builditbiggerbackend.neijaywarner.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /** Gets the next joke */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();
        response.setData("Joke:" + JokeFactory.newJoke());
        //TODO: The idea would be to flesh it out by having a Joke class instead of MyBean and
        // checking it out with stethos or PostMan/Charles
        // Perhaps even for fun confirm that we could write an Android app to access that endpoint
        // with Retrofit instead.
        return response;
    }

}
