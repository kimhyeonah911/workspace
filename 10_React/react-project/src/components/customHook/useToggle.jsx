import React, { useState } from 'react'

const useToggle = (init = false) => {
    const [value, setValue] = useState(init);

    const toggle = () => {
        setValue(prev => !prev);
    }

    return [value, toggle];
}

export default useToggle