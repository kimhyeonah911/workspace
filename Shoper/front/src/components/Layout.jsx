import React from 'react';
import styled from 'styled-components';
import Header from './Header';
import Footer from './footer';

const Layout = ({ children }) => {
  return (
    <>
      <Header />
      <Content>{children}</Content>
      <Footer />
    </>
  );
};

const Content = styled.main`
  min-height: calc(100vh - 60px);
`;

export default Layout;
