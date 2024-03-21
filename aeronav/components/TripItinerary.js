import Link from "next/link"
import "@/styles/Itineraries.css"

function TripItinerary({Trip}) {
    return (
<div className="Trip-Itinerary-Wrapper">
  <div className="Top-Wrapper">
  <h1 className="Itinerary-Title">
    {Trip.tripName}
  </h1>
  <p>{Trip.start} - {Trip.end}</p>
  </div>
  <div className="Days-Wrapper">
    <div className="Day-Wrapper">
      <h2 className="Day-title">Day 1</h2>
      <div className="activities-wrapper">
          <div className="activity-wrapper">10:30-11:30am: Flight to Colorado</div>
          <div className="activity-wrapper">11:30-12:30pm: Hotel Check-in</div>
          <div className="activity-wrapper">10:30-11:30am: Flight to Colorado</div>
          <div className="activity-wrapper">10:30-11:30am: Flight to Colorado</div>
      </div>
    </div>
    <div className="Day-Wrapper">
      <h2 className="Day-title">Day 2</h2>
      <div className="activities-wrapper">
          <div className="activity-wrapper">10:30-11:30am: Activity 1</div>
          <div className="activity-wrapper">10:30-11:30am: Activity 2</div>
          <div className="activity-wrapper">10:30-11:30am: Activity 3</div>
          <div className="activity-wrapper">10:30-11:30am: Activity 4</div>
      </div>
    </div>
    <div className="Day-Wrapper">
      <h2 className="Day-title">Day 3</h2>
      <div className="activities-wrapper">
          <div className="activity-wrapper">10:30-11:30am: Activity 1</div>
          <div className="activity-wrapper">10:30-11:30am: Activity 2</div>
          <div className="activity-wrapper">10:30-11:30am: Hotel Checkout</div>
          <div className="activity-wrapper">10:30-11:30am: Flight Home</div>
      </div>
    </div>
  </div>
</div>
    )
}

export default TripItinerary