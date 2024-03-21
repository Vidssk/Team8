import '@/styles/Itineraries.css'
import TripItinerary from "@/components/TripItinerary.js";
import Trip from '@/Data/Trip.js';
  // Add more trip objects as needed
const page = () => {
  return (
    <div className='Itenerary-Wrapper'>
      <TripItinerary Trip={Trip}/>
  </div>

  )
}

export default page