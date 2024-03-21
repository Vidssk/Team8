import Link from "next/link"
import "@/styles/Itineraries.css"

function Itinerary({Trip}) {
    return (
      <Link href={Trip.href}>
          <div className="Itinerary-Link-Wrapper">
        <h1 className='TripName'>{Trip.tripName}</h1>
        <div className='start-end-Wrapper'>
            <h3 className='start-Wrapper'>Start Date: {Trip.start}</h3>
            <h3 className='end-Wrapper'>End Date: {Trip.end}</h3>
        </div>
        <div className='Location-Wrapper'>
            <h3 className='Start-Location-Wrapper'> Start Location: {Trip.startLocation}</h3>
            <h3 className='End-Location-Wrapper'>End Location: {Trip.endLocation}</h3>
        </div>
      <p>Budget {Trip.budget}</p>
        </div>
        </Link>
    )
}

export default Itinerary