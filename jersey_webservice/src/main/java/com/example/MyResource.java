package com.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.j2se.BinarySearch;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        int b[] = {0, 1, 2, 3, 4, 5}; // a[4] = 4, a[5]=5;

        return "Got it!" + BinarySearch.binary_search(b, 5);
    }
}
