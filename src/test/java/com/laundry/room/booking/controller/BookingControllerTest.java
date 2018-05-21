package com.laundry.room.booking.controller;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.laundry.room.booking.LaundryRoomBookingApplication;
import com.laundry.room.booking.event.BookingEvent;
import com.laundry.room.booking.event.BookingTimeslot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LaundryRoomBookingApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class BookingControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void createBookingTest() throws Exception{
        mockMvc.perform(post("/api/v1/booking")
                // .content(this.json(new BookingEvent(1, "201", timeslotSuccess)))
                .content("    {\n" +
                        "        \"laundryRoomId\": 1,\n" +
                        "        \"apartmentNumber\": \"201\",\n" +
                        "        \"timeslot\": { \n" +
                        "        \"from\": \"2018-05-25T10:00:00\",\n" +
                        "        \"to\": \"2018-05-25T12:00:00\"\n" +
                        "    }}")
                .contentType(contentType))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/v1/booking")
                // .content(this.json(new BookingEvent(1, "201", timeslotSuccess)))
                .content("    {\n" +
                        "        \"laundryRoomId\": 1,\n" +
                        "        \"apartmentNumber\": \"201\",\n" +
                        "        \"timeslot\": { \n" +
                        "        \"from\": \"2018-05-25T19:00:00\",\n" +
                        "        \"to\": \"2018-05-25T20:00:00\"\n" +
                        "    }}")
                .contentType(contentType))
                .andExpect(status().isConflict());

        mockMvc.perform(post("/api/v1/booking")
                // .content(this.json(new BookingEvent(1, "201", timeslotSuccess)))
                .content("    {\n" +
                        "        \"laundryRoomId\": 1,\n" +
                        "        \"apartmentNumber\": \"301\",\n" +
                        "        \"timeslot\": { \n" +
                        "        \"from\": \"2018-05-25T09:00:00\",\n" +
                        "        \"to\": \"2018-05-25T10:00:00\"\n" +
                        "    }}")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBookingsTest() throws Exception {
        mockMvc.perform(get("/api/v1/booking"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].apartment_number", is("201")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].apartment_number", is("202")));
    }

    @Test
    public void getBookingsByLaundryRoomIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/booking?laundry-room-id=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].apartment_number", is("201")));
    }

    @Test
    public void cancelBookingTest() throws Exception {
        mockMvc.perform(delete("/api/v1/booking/1"))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}