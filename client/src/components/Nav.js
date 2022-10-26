import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const NavWrapper = styled.nav`
  width: 164px;
  height: 100vh;
  padding: 24px 0px 0px 0px;
  background-color: white;
`;
const NavContainerBox = styled.ol`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 0;
  padding: 0;
  .selected {
    color: black;
    font-weight: 800;
    background-color: #f1f2f3;
    border-right: 3.5px solid #f48225;
    > svg {
      fill: black;
    }
  }
  .blank {
    padding: 4px 4px 4px 32px;
  }
`;
const NavContainer = styled.li`
  list-style: none;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  width: 164px;
  height: 34px;
  padding: 8px 4px 4px 8px;
  background-color: white;
  color: #525960;
  font-size: 13px;
  > svg {
    fill: #9fa6ad;
    margin: -1px 4px 0px 0px;
  }
  &:hover {
    color: black;
    > svg {
      fill: black;
    }
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
            <svg aria-hidden="true" width="18" height="18" viewBox="0 0 18 18">
              <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8ZM8 15.32a6.46 6.46 0 0 1-4.3-2.74 6.46 6.46 0 0 1-.93-5.01L7 11.68v.8c0 .88.12 1.32 1 1.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44 0 1-.56 1-1V5h2c.88 0 1.4-.72 1.4-1.6v-.33a6.45 6.45 0 0 1 3.83 4.51 6.45 6.45 0 0 1-1.51 5.73v.01Z"></path>
            </svg>
            <span>Questions</span>
          </NavContainer>
        </StyledLink>
        <StyledLink to="/tags">
          <NavContainer
            className={curPage === 2 ? 'selected blank' : 'blank'}
            onClick={handleClickTags}
          >
            Tags
          </NavContainer>
        </StyledLink>
        <StyledLink to="/users">
          <NavContainer
            className={curPage === 3 ? 'selected blank' : 'blank'}
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
