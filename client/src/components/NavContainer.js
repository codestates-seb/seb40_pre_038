import Nav from './Nav';
import styled from 'styled-components';

const NavContainerWrapper = styled.div`
  display: flex;
  flex-direction: row;
`;

const NavContainer = ({ children }) => {
  return (
    <NavContainerWrapper>
      <Nav />
      <div>{children}</div>
    </NavContainerWrapper>
  );
};

export default NavContainer;
