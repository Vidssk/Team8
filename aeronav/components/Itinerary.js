import Link from "next/link"

function Itinerary( {href, text}) {
    return (
        <Link href={href}>
            {text}

        </Link>
    )
}

export default Itinerary