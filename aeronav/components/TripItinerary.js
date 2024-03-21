import React from 'react';
// import Trip from '@/Data/Trip.js'




function TripItinerary ({Trip}){
//   const totalDuration = activities.length > 0 ? activities.reduce((acc, activity) => acc + activity.duration, 0) : 0;

  return (
    <div className='Trip-Itinerary-Wrapper'>
      {/* <p>Total duration: {totalDuration} hours</p> */}
      <h1 className='Itinerary-Title'>{Trip.tripName}</h1>
      {/* {activities.length > 0 ? (
        <ul>
          {activities.map((activity, index) => (
            <li key={index}>
            </li>
          ))}
        </ul> 
        ) : (
      )}
        */}
        {/* <p>No activities available</p> */}
    </div>
  );
};

export default TripItinerary;
