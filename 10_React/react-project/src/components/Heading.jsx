import React from 'react'

const Heading = (props) => {
    if(props.type === "h2"){
        return <h2>안녕하세요. Props입니다.</h2>
    }
    return (
        <>
            <h1>안녕하세요. Props입니다.</h1>
            <h2>{props.children}</h2>
        </>
    )
}

export default Heading;