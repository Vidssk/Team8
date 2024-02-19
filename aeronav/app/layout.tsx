// import '@styles/globals.css';
import Navbar from "@/components/Navbar"
import Footer from "@/components/Footer"
export const metadata = {
  title: "Santiagos Portfolio",
  description: 'Relentless Software Developer'
}

const Rootlayout = ({children,}:{children: React.ReactNode}) => {
  return (
    <html lang='en'>
      <body>
        <div className='main'>
      <Navbar />
        <main className='app'>
        {/* <video src = {require("../public/videos/backgroundVideo.mp4")} autoPlay loop muted /> */}
          {children}
        </main>
      <Footer />
        </div>
      </body>
    </html>
  )
}

export default Rootlayout;
