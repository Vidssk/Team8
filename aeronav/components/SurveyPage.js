
import React from 'react';
import '../styles/Survey.css'

const HeroContainer = () => {
  return (
    <section className="main-page-container">
      <div className='quote-container'><h1>Where travel dreams take flight.</h1></div>
      <div className='survey-container'>
        <h1>Survey</h1>
        <form className='location-wrapper'>
      <label> Start Location: 
        <input type="text" />
      </label>
      <label> End Location: 
        <input type="text"/>
      </label>
    </form>
    <div className='budget-container'>Budget</div>
        <form className='duration-wrapper'>
      <label>Start Date: 
        <input type="text" />
      </label>
      <label> End Date: 
        <input type="text"/>
      </label>
    </form>
    <form>
      <button className='button-wrapper'>Options</button>
    </form>
    <form>
      <button className='button-wrapper'>Generate Packages</button>
    </form>

      </div>
      {/* <div className='ad-carousel-container'>
        Ad-Carousel-Container
      </div> */}

  </section>
  )
}

export default HeroContainer;