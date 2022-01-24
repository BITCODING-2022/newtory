import React, { useState, useEffect } from 'react';
import axios from 'axios';

import '../css/calendar.css'

import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';



function Calendar() {

  const months = {
    'January' : '1',
    'February' : '2',
    'March' : '3',
    'April' : '4',
    'May' : '5',
    'June' : '6',
    'July' : '7',
    'August' : '8',
    'September' : '9',
    'October' : '10',
    'November' : '11',
    'December' : '12'
  }

  const baseUrl = "http://localhost:8080/calendar";

  function getMonthlyTodos(e) {
    console.log("조회 시작");

    const strDate = e.split(" ");
    let requestData = new Object();
    requestData.year = strDate[1];
    requestData.month = months[strDate[0]];
    console.log(requestData);

    console.log(JSON.parse(requestData));
    const addTodoDetail = async () =>{
    await axios
      .post("http://localhost:8080/calendar", requestData,
        {headers: {"Content-Type": `application/json`,}})
      .then((response) => {
          console.log(response.data);
      })
    }
    console.log("조회 완료");
  }

  return (
    <div className="main-calendar">
      <div>
        <FullCalendar
          plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
          initialView="dayGridMonth"
          datesSet={(args) => {
            console.log(args.view.title);
            getMonthlyTodos(args.view.title);
            }
          }
        />
      </div>
    </div>
  );
}

export default Calendar;