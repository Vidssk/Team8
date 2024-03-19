import '@/styles/Itineraries.css'
import Itinerary from '@/components/Itinerary'

const page = () => {
  return (
    <div className='Itineraries-Page-Wrapper'>
        <div className='Itineraries-Wrapper'>
        <h1 className='Itineraries-Title'>Trip Itineraries</h1>
        <ul className='Itineraries-list'>
          <li><Itinerary href="/trip1" text="Trip to florida"/></li>
        </ul>
        </div>
  </div>

  )
}

export default page