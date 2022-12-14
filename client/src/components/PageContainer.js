import { useLocation } from 'react-router-dom';
import styled from 'styled-components';

import Nav from './Nav';
import Sidebar from './Sidebar';
import Footer from './Footer';

const Container = styled.div`
  margin-top: 0;
  max-width: 1264px;
  width: 100%;
  background: none;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  position: relative;
  flex: 1 0 auto;
  text-align: left;

  &.bg-black-025 {
    background-color: #f8f9f9 !important;
  }
`;

const Content = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  display: flex;
  justify-content: flex-start;
  background-color: #ffffff;
  border-radius: 0;
  border: 1px solid #d6d9dc;
  border-top-width: 0;
  border-bottom-width: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: 24px;
  box-sizing: border-box;
  margin: 0 auto;

  &.d-flex {
    display: flex !important;
  }
  &.flex__center {
    justify-content: center !important;
    align-items: center !important;
  }
  &.pt0 {
    padding-top: 0 !important;
  }

  &:before,
  &:after {
    content: '';
    display: table;
  }

  @media screen and (max-width: 640px) {
    width: 100%;
    border-left: 0;
    border-right: 0;
  }
  @media screen and (max-width: 980px) {
    padding-left: 16px;
    padding-right: 16px;
    display: block;
  }
`;

const PageContainer = ({
  children,
  nav,
  sidebar,
  footer,
  containerClassName,
  contentClassName,
}) => {
  const location = useLocation();

  return (
    <>
      <Container className={containerClassName}>
        {nav ? <Nav pathname={location.pathname} /> : null}
        <Content className={contentClassName}>
          {children}
          {sidebar ? <Sidebar /> : null}
        </Content>
      </Container>
      {footer ? <Footer /> : null}
    </>
  );
};

export default PageContainer;
