import React, {useEffect, useRef, useState} from 'react'

const UseRefTest = () => {
    const [name, setName] = useState("");
    const [gender, setGender] = useState("man");

    const useInput = useRef(null);
  
    const handleChangeName = (ev) => {
        setName(ev.target.value);
    }
  
    const handleChangeGender = (ev) => {
        setGender(ev.target.value);
    }
  
    const handelSubmit = (ev) => {
        ev.preventDefault(); //a태그나 submit태그 같은 고유동작을 중단하기 위한 이벤트 방지
        //ev.stopPropagation(); 부모 엘리먼트에게 이벤트가 전파되는 것을 막을 수 있음
        alert(`이름 : ${name}\n성별 : ${gender === 'man' ? '남자' : '여자'}`);
    };

    const handelReset = () => {
        setName("");
        setGender("man");
    };

    //초기화 버튼을 눌렀을 때 state가 변경되므로 화면이 리렌더링 되면 input 포커스 설정

    useEffect(() => {
        useInput.current?.focus();
    }, [name, gender])
    //name 또는 gender의 값이 변경되면 해당 함수를 한 번 실행해줘
  
    return (
        <form onSubmit={handelSubmit}>
            <label htmlFor="">
                이름 : 
                <input type="text" value={name} onChange={handleChangeName}/>
            </label>
            <br/><br/>
            <label>
                성별 :
                <select value={gender} onChange={handleChangeGender} ref={useInput}>
                    <option value="man">남자</option>
                    <option value="woman">여자</option>
                </select>
            </label>
            <br/><br/>
            <button type='submit'>제출</button>
            <button type='button' onClick={handelReset}>초기화</button>
        </form>
    )
}

export default UseRefTest