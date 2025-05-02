import React, { useEffect, useState } from 'react';
import { BeatLoader } from 'react-spinners';
import styled from 'styled-components';

const LoaderDemo = () => {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    //요청 후 2초 뒤에 데이터 도착
    const timer = setTimeout(() => setLoading(false), 2000);
    return () => clearTimeout(timer);
  }, []);

  return (
    <Wrapper>
      {loading ? (
        <SpinnerWrapper>
          <BeatLoader size={30} color="white" />
          <p>불러오는 중...</p>
        </SpinnerWrapper>
      ) : (
        <Content>
          <h2>데이터 로딩 완료!</h2>
          <p>이제 본문을 확인하세요 :)</p>
        </Content>
      )}
    </Wrapper>
  );
};

export default LoaderDemo;

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 300px;
  flex-direction: column;
`;

const Content = styled.div`
  text-align: center;
`;

const SpinnerWrapper = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  color: black;
`;
