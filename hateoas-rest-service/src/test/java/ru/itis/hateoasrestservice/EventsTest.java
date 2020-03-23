package ru.itis.hateoasrestservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoasrestservice.models.Event;
import ru.itis.hateoasrestservice.services.EventsService;

import java.sql.Timestamp;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class EventsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventsService eventsService;

    @BeforeEach
    public void setUp() {
        when(eventsService.appointment(1L)).thenReturn(assignedEvent());
    }

//    // Assigned notAssigned Canceled notObserved Finished
//    private String state;
//
//    @ManyToOne
//    @JoinColumn(name = "shedule_id")
//    private Shedule shedule;

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/events/1/appointment")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(assignedEvent().getTitle()))
                .andExpect(jsonPath("$.state").value(assignedEvent().getState()))
                .andExpect(jsonPath("$.eventLineNumber").value(assignedEvent().getEventLineNumber()))
                .andExpect(jsonPath("$.averageDuration").value(assignedEvent().getAverageDuration()))
                .andExpect(jsonPath("$.realDuration").value(assignedEvent().getRealDuration()))
                .andExpect(jsonPath("$.eventStartTime").value(assignedEvent().getEventStartTime()))
                .andExpect(jsonPath("$.recordingIsAvailableUntil").value(assignedEvent().getRecordingIsAvailableUntil()))
                .andDo(document("publish_course", responseFields(
                        fieldWithPath("title").description("Название мероприятия"),
                        fieldWithPath("state").description("Состояние записи"),
                        fieldWithPath("eventLineNumber").description("Номер в очереди"),
                        fieldWithPath("averageDuration").description("Ожидаемая продолжительность приема"),
                        fieldWithPath("realDuration").description("Реальная продолжительность приема"),
                        fieldWithPath("eventStartTime").description("Время приема"),
                        fieldWithPath("recordingIsAvailableUntil").description("До какого времени место в очереди доступно к записи")
                )));
    }

    private Event assignedEvent() {
        return Event.builder()
                .id(1L)
                .eventLineNumber(12)
                .state("ASSIGNED")
                .title("Очередь к терапевту")
                .eventStartTime(new Timestamp(2020,3,23,17,40,0,0))
                .recordingIsAvailableUntil(new Timestamp(2020,3,23,17,10,0,0))
                .averageDuration(15L)
                .realDuration(2L)
                .build();
    }

}
