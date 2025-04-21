import './App.css'
import Hello from './components/Hello'
import Heading from './components/Heading'
import VideoList from './components/VideoList'

const videoData = [{
  sumbnail:"https://i.ytimg.com/an_webp/sQNvtxKMX3w/mqdefault_6s.webp?du=3000&sqp=CKCJh8AG&rs=AOn4CLB4pDgHnLcG8kXde_y6HumM8PYcjQ",
  title: "춘봉이가 열일하는 영상",
  logo: "https://yt3.ggpht.com/QY2puTmO1IWzHiU4Tmq9LoLnVTzbEdaxNwrddNtMKil9UxLBxH_gX9qOeDNuTOMVi1aS0w4SGjc=s48-c-k-c0x00ffffff-no-rj",
  channelName: "언더월드 UNDER WORLD",
  views: '246만',
  date: "3개월 전",
},{
  sumbnail:"https://i.ytimg.com/vi/7cW-K0j1ZFw/hqdefault.jpg?sqp=-oaymwFBCNACELwBSFryq4qpAzMIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB8AEB-AH-CYAC0AWKAgwIABABGFQgZShjMA8=&rs=AOn4CLBIrdwL6MOKppQpA7GE1BPKBN5C_Q",
  title: "진격의거인 4기 3쿨 전편 라이너 갑옷거인 변신 씬",
  logo: "https://yt3.googleusercontent.com/c7JYz5QFAK0dFEQ_nvy5AvMRZwaw8Of2016fSJvWrAfv7HicJ0iufRB_ECoRx5t1Nccb-ZX9Sg=s160-c-k-c0x00ffffff-no-rj",
  channelName: "이름없음2020",
  views: '27만회',
  date: "2년 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/HCAf3UBzZhA/mqdefault_6s.webp?du=3000&sqp=CKCgh8AG&rs=AOn4CLD7csgE5NW7JeT0b582gYbcbDFQtw",
  title: "[진격의 거인 4기 3쿨 전편] 한지 초대형 거인 전투씬 (KR SUB)",
  logo: "https://yt3.googleusercontent.com/ozi50OLRq-1rwNK_WFGP3ye0dkV1yTIf09E62Lg81zJh26nQytMr4h_EE_sWlGo2pldqR5PYaA=s160-c-k-c0x00ffffff-no-rj",
  channelName: "Pharmacist",
  views: '88만',
  date: "2년 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
  title: "빵빵이와 옥지의 진솔한 대화(물리)",
  logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
  channelName: "빵빵이의 일상",
  views: '8.3만',
  date: "2시간 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
  title: "빵빵이와 옥지의 진솔한 대화(물리)",
  logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
  channelName: "빵빵이의 일상",
  views: '8.3만',
  date: "2시간 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
  title: "빵빵이와 옥지의 진솔한 대화(물리)",
  logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
  channelName: "빵빵이의 일상",
  views: '8.3만',
  date: "2시간 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
  title: "빵빵이와 옥지의 진솔한 대화(물리)",
  logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
  channelName: "빵빵이의 일상",
  views: '8.3만',
  date: "2시간 전",
},{
  sumbnail:"https://i.ytimg.com/an_webp/-lbqhtDyfe4/mqdefault_6s.webp?du=3000&sqp=CJ60h8AG&rs=AOn4CLAtde60hz-u8zCIWVaYD1tZRsB5fQ",
  title: "빵빵이의 황금벚꽃",
  logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
  channelName: "빵빵이의 일상",
  views: '2만',
  date: "4시간 전",
}]

function App() {
  return (
    <>
    {/*
      <Heading/>
      <Heading>
        무엇을 도와드릴까요?
      </Heading>
      <Hello/>*/}
      <VideoList videos={videoData}/>
    </>
  )
}

export default App
