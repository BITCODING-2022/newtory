import React from 'react';
import  {Route} from 'react-router-dom';
import {Routes} from 'react-router-dom';
import Calendar from '../components/calendar';
import Header from '../components/header';

function MainPage() {
  
    return (
    <div className="App">
        <Header/>
        <Routes>
            <Route exact path="/" element={<Calendar/>}/>
        </Routes>
    </div>
    );
}

  export default MainPage;