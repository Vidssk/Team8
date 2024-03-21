import '@/styles/Itineraries.css'
import Itinerary from '@/components/Itinerary'

const trips = [
  {
    href: "/itinerary",
    tripName: "Winter Break",
    start: "12/01/2024",
    end: "12/27/2024",
    startLocation: "DFW Airport",
    endLocation: "Colorado Springs Airport",
    budget: "$12,000"
  },
  {
    href: "/itinerary",
    tripName: "Spring Break",
    start: "03/25/2024",
    end: "05/02/2024",
    startLocation: "DFW Airport",
    endLocation: "Colorado Springs Airport",
    budget: "$12,000"
  },
  // Add more trip objects as needed
];
const page = () => {
  return (
    <div className='Itineraries-Page-Wrapper'>
        <div className='Itineraries-Wrapper'>
        <h1 className='Itineraries-Title'>Trip Itineraries</h1>
        <ul className='Itineraries-list'>
          {trips.map((trip,index) => (
            <li key={index}>
              <Itinerary
                Trip={trip}
                // href={trip.href}
                // TripName={trip.tripName}
                // start={trip.start}
                // end={trip.end}
                // startLocation={trip.startLocation}
                // endLocation={trip.endLocation}
                // budget={trip.budget}
                />  
            </li>
          ))}
        </ul>
        </div>
  </div>

  )
}

export default page