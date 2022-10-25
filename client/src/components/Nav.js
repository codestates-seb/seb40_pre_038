import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const NavWrapper = styled.nav`
  position: fixed;
  width: 164px;
  height: 100vh;
  padding: 24px 0px 0px 0px;
  border-right: 1px solid #d6d9dc;
  background-color: white;
  .selected {
    color: black;
    font-weight: 800;
    background-color: #f1f2f3;
    border-right: 3.5px solid #f48225;
  }
`;
const NavContainerBox = styled.ol`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0;
  padding: 0;
`;
const NavContainer = styled.li`
  list-style: none;
  box-sizing: border-box;
  width: 164px;
  height: 34px;
  padding: 8px 4px 4px 8px;
  background-color: white;
  color: #525960;
  font-size: 13px;
  &:hover {
    color: black;
  }
`;
const Public = styled.li`
  list-style: none;
  box-sizing: border-box;
  width: 156px;
  height: 15px;
  margin: 16px 0px 4px 8px;
  background-color: white;
  color: #6a737c;
  font-size: 11px;
`;
const StyledLink = styled(Link)`
  text-decoration: none;
  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    color: inherit;
    text-decoration: none;
  }
`;

const Nav = () => {
  const [curPage, setCurPage] = useState(0);
  const handleClickHome = () => {
    setCurPage(0);
  };
  const handleClickQuestions = () => {
    setCurPage(1);
  };
  const handleClickTags = () => {
    setCurPage(2);
  };
  const handleClickUsers = () => {
    setCurPage(3);
  };
  return (
    <NavWrapper>
      <NavContainerBox>
        <StyledLink to="/">
          <NavContainer
            className={curPage === 0 ? 'selected' : ''}
            onClick={handleClickHome}
          >
            Home
          </NavContainer>
        </StyledLink>
      </NavContainerBox>
      <NavContainerBox>
        <Public>PUBLIC</Public>
        <StyledLink to="/questions">
          <NavContainer
            className={curPage === 1 ? 'selected' : ''}
            onClick={handleClickQuestions}
          >
            Questions
          </NavContainer>
        </StyledLink>
        <StyledLink to="/tags">
          <NavContainer
            className={curPage === 2 ? 'selected' : ''}
            onClick={handleClickTags}
          >
            Tags
          </NavContainer>
        </StyledLink>
        <StyledLink to="/users">
          <NavContainer
            className={curPage === 3 ? 'selected' : ''}
            onClick={handleClickUsers}
          >
            Users
          </NavContainer>
        </StyledLink>
      </NavContainerBox>
    </NavWrapper>
  );
};

export default Nav;
