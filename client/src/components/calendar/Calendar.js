import axios from 'axios';

import '../../css/calendar/calendar.css';
import EventModal from './EventModal';

import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import { useEffect, useState } from 'react';



function Calendar() {

  let monthlyTodos = []

  const [events, setEvents] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [clickEvent, setClickEvent] = useState("");

  const closeModal = () => {
    setModalOpen(false);
  };
  

  const baseUrl = "http://localhost:8080/";

  const getMonthlyTodos = () => {
    console.log("조회 시작");
    const request = async () => {
      await axios
        .get(baseUrl + "todolist/monthly")
        .then((response) => {
          console.log(response.data);
          addEvents(response.data);
        })
      }
    request();
  }

  const addEvents = (responseData) => {
    responseData.map(e => (
      monthlyTodos.push({
        id: e.id,
        title: e.title,
        start: e.startDate,
        end: e.endDate,
        color: "#" + Math.round(Math.random() * 0xFFFFFF).toString(16)
      })
    ))
    console.log(monthlyTodos);
    setEvents(monthlyTodos);
  }

  const handleEventClick = (clickInfo) => {
    setModalOpen(true);
    setClickEvent(clickInfo.event.title);
  }

  useEffect(() => {
    getMonthlyTodos()
  },[]);

  return (
    <div className="main-calendar">
      <div>
        <FullCalendar 
          defaultView="dayGridMonth" 
          plugins={[ dayGridPlugin ]}
          events={events}
          eventClick={handleEventClick}
        />
      </div>
      <EventModal open={modalOpen} close={closeModal} header="일정 내용">
        {clickEvent}
      </EventModal>
    </div>
  );
}

export default Calendar;