import '@/styles/Itineraries.css'
import TripItinerary from "@/components/TripItinerary.js";
import ItineraryExample from '@/Data/ItineraryExample';
  // Add more trip objects as needed

const page = () => {
  return (
    <div className='Itinerary-Wrapper'>
    <ul className='Itinerary-list'>
          {ItineraryExample.map((trip,index) => (
            <li key={index}>
              <TripItinerary
                Trip={trip}
                />  
            </li>
          ))}
        </ul>
  </div>

  )
}

export default page