import React from 'react'
import Link from 'next/link'
import '@/styles/Footer.css'
import { faInstagram, faFacebook, faLinkedin, faGithub} from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { width } from '@fortawesome/free-brands-svg-icons/fa42Group';




function Footer() {
    return (
        <footer className='footer-container'>
            <div className='waves'>                    
                <div className='wave' id='wave1'/>
                <div className='wave' id='wave2'/>
                <div className='wave' id='wave3'/>
                <div className='wave' id='wave4'/>
            </div>
            <ul className='social-icon'>

             

            <Link className="social-icon-link"
                href={{ pathname: "https://github.com/Vidssk/Team8"}}
                target="_blank"
                aria-label='GitHub'
            >
                <FontAwesomeIcon style={{width: '40px',height: 'fit-content'}} icon={faGithub}/>
            </Link>

            {/* <Link className="social-icon-link"
                href={{ pathname: "https://www.facebook.com/profile.php?id=100002036873433"}}
                target="_blank"
                aria-label='Facebook'
            >
                <FontAwesomeIcon style={{width: '40px',height: 'fit-content'}} icon={faFacebook}/>
            </Link> */}
            </ul>
            <small className="website-rights">Team 8 | AeroNav | © 2024</small>
        </footer>
    )
}

export default Footer
