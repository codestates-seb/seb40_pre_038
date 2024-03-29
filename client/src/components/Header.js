import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { HeaderSearchbar } from './Searchbar';
import { ButtonBlue, ButtonSblue } from './Buttons.js';

const HeaderContinaer = styled.div`
  box-sizing: border-box;
  position: fixed !important;
  left: 0 !important;
  top: 0 !important;
  min-width: auto;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 1px 2px 0px,
    rgba(0, 0, 0, 0.05) 0px 1px 4px 0px, rgba(0, 0, 0, 0.05) 0px 2px 8px 0px;
  width: 100%;
  z-index: 5050;
  background-color: #f8f9f9;
  height: 50px;
  display: flex;
  border-top: 3px solid #f48225;
  align-items: center;

  /* justify-content: center;

  @media screen and (max-width: 640px) {
    justify-content: space-between;
  } */
`;

const HeaderTopbarContainer = styled.div`
  width: 97.2307692rem;
  max-width: 100%;
  height: 100%;
  display: flex;
  margin: 0 auto;
  align-items: center;
  box-sizing: border-box;
`;

const MiniMenuBtnContainer = styled.button`
  display: none;
  @media screen and (max-width: 640px) {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    padding: 0 16px;
    border: none;
    background-color: #f8f9f9;
    .fa-regular {
      font-size: 16px;
      color: #525960;
      font-weight: 600;
    }
    &:hover {
      background-color: #e3e5e8;
      cursor: pointer;
    }
  }
`;

const MiniSearchBtnContainer = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 0 10px;
  border: none;
  background-color: #f8f9f9;

  @media screen and (min-width: 641px) {
    display: none;
  }
  &:hover {
    background-color: #e3e5e8;
    cursor: pointer;
  }
`;

const LogoContainer = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 8px 3px;

  @media screen and (max-width: 640px) {
    display: none;
  }
  img {
    width: 150px;
    height: 30px;
  }
  &:hover {
    background-color: #e3e5e8;
  }
`;

const MiniLogoContainer = styled.div`
  display: none;
  @media screen and (max-width: 640px) {
    display: flex;
    height: 100%;
    align-items: center;
    justify-content: center;
    padding: 0 8px;
    &:hover {
      background-color: #e3e5e8;
    }
  }
`;

const ProductsBtnContainer = styled.div`
  width: 82px;
  height: 33px;
  margin: 0 0 3px 6px;
  padding-top: 3px;

  .product_btn {
    width: 71px;
    height: 29px;
    background-color: #f8f9f9;
    color: #525960;
    font-size: 13px;
    font-weight: 400;
    border: none;
    border-radius: 20px;
    @media screen and (max-width: 640px) {
      font-size: 11px;
    }
    &:hover {
      background-color: #e3e5e8;
      color: black;
      cursor: pointer;
    }
  }
`;

const UserIconConatiner = styled.li`
  height: 100%;
  list-style: none;
  display: flex;
  align-items: center;
  padding: 0 12px;

  .user_reputation {
    font-size: 12px;
    font-weight: 700;
    color: #525960;
    padding: 0 3px;
  }

  .user_badges {
    color: #d1a784;
    display: flex;
    align-items: center;

    .dot {
      font-size: 20px;
      font-weight: 900;
      margin: 0 3px;
      padding-bottom: 2px;
    }
    .count {
      font-size: 12px;
      font-weight: 400;
    }
  }

  &:hover {
    background-color: #e3e5e8;
  }
`;

const UserIconImg = styled.div`
  width: ${(props) => props.width || '24px'};
  height: ${(props) => props.height || '24px'};

  img,
  svg {
    width: 100%;
    height: 100%;
  }
`;

const UserProfileIcon = ({ width, height }) => {
  return (
    <UserIconImg width={width} height={height}>
      <img
        src={process.env.PUBLIC_URL + '/images/user_icon.png'}
        alt="user profile icon"
      ></img>
    </UserIconImg>
  );
};

const UserInfo = styled.div`
  width: 32px;
  display: flex;
  align-items: center;
  @media screen and (max-width: 640px) {
    display: none;
  }
`;

const HeaderIcon = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-basis: 40px;
  height: 47px;
  padding: 0 10px;

  &:hover {
    background-color: #e3e5e8;
    cursor: pointer;
    svg {
      fill: black;
    }
  }
`;

const InboxIconContainer = styled(HeaderIcon)``;
const AchieveIconContainer = styled(HeaderIcon)``;
const HelpIconContainer = styled(HeaderIcon)``;
const CommunityIconContainer = styled(HeaderIcon)`
  height: 45px;
`;

const Header_Left = styled.div`
  display: flex;
  align-items: center;
  height: 47px;
  flex-grow: 1;
  max-width: 993px;
`;

const Header_Right = styled.div`
  display: flex;
  align-items: center;
  //width: 234px;
  height: 47px;
  position: relative;
`;

const MenuDropdown = styled.div`
  width: 375px;
  position: absolute;
  top: 50px;
  right: 0px;
  background-color: white;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
`;
const CommunityDropdown = styled(MenuDropdown)``;
const HelpDropdown = styled(MenuDropdown)`
  width: 213px;
  right: 38px;
`;

const DropdownSubTitle = styled.div`
  width: 100%;
  height: 30px;
  font-size: 11px;
  font-weight: 700;
  color: #0074cc;
  display: flex;
  align-items: center;
  padding-left: 10px;
  background-color: #f1f2f3;
  &:hover {
    cursor: pointer;
    color: hsl(206, 100%, 52%);
  }
`;

const DropdownSubContent = styled.div`
  width: 100%;
  height: 60px;
  font-size: 12px;
  font-weight: 400;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding-left: 8px;
  .name {
    color: #0074cc;
    font-weight: 700;
  }
  .discription {
    color: #3b4045;
    padding: 2px 0;
    &:hover {
      cursor: pointer;
    }
  }
  .loginout {
    color: #0074cc;
    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
`;
const HelpDropdownSubContent = styled(DropdownSubContent)`
  border-bottom: 1px solid #e3e6e8;
  span.name {
    font-weight: 600;
    &:hover {
      cursor: pointer;
      color: hsl(206, 100%, 52%);
    }
  }
  font-weight: 400;
  &:hover {
    background-color: #e3e6e8;
  }
`;

const AccountBtnsWrapper = styled.div`
  display: flex;

  button {
    cursor: pointer;
  }
  .signup {
    margin-left: 4px;
  }
`;
const LoginBtn = styled(ButtonSblue)``;
const SignupBtn = styled(ButtonBlue)``;

const Header = ({ isLogin, setIsLogin }) => {
  // inbox, achieve, help, community
  const [clickedMenu, setClickedMenu] = useState(null);

  const handleClickMenu = (menu) => {
    if (menu === clickedMenu) setClickedMenu(null);
    else setClickedMenu(menu);
    console.log(clickedMenu);
  };

  return (
    <HeaderContinaer>
      <HeaderTopbarContainer>
        <Header_Left>
          <MiniMenuBtnContainer>
            <i className="fa-regular fa-bars"></i>
          </MiniMenuBtnContainer>
          <LogoContainer>
            <Link to="/">
              <img
                src={process.env.PUBLIC_URL + '/images/main_logo.svg'}
                alt="Stack Overflow logo"
              ></img>
            </Link>
          </LogoContainer>
          <MiniLogoContainer>
            <Link to="/">
              <svg
                aria-hidden="true"
                width="25"
                height="30"
                viewBox="0 0 32 37"
              >
                <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB" />
                <path
                  d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                  fill="#F48024"
                />
              </svg>
            </Link>
          </MiniLogoContainer>
          <ProductsBtnContainer>
            <button className="product_btn">Products</button>
          </ProductsBtnContainer>
          <HeaderSearchbar></HeaderSearchbar>
        </Header_Left>
        <Header_Right>
          <MiniSearchBtnContainer>
            <svg width="18" height="18" viewBox="0 0 18 18">
              <path
                style={{ fill: 'rgb(82, 89, 96)' }}
                d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
              />
            </svg>
          </MiniSearchBtnContainer>
          {isLogin ? (
            <>
              <UserIconConatiner>
                <Link to="/">
                  <UserProfileIcon></UserProfileIcon>
                </Link>
                <UserInfo>
                  <span className="user_reputation">1</span>
                  <div className="user_badges">
                    <span className="dot">•</span>
                    <span className="count">1</span>
                  </div>
                </UserInfo>
              </UserIconConatiner>
              <InboxIconContainer>
                <svg
                  aria-hidden="true"
                  className="svg-icon iconInbox"
                  width="20"
                  height="18"
                  viewBox="0 0 20 18"
                  fill="#525960"
                >
                  <path d="M4.63 1h10.56a2 2 0 0 1 1.94 1.35L20 10.79V15a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-4.21l2.78-8.44c.25-.8 1-1.36 1.85-1.35Zm8.28 12 2-2h2.95l-2.44-7.32a1 1 0 0 0-.95-.68H5.35a1 1 0 0 0-.95.68L1.96 11h2.95l2 2h6Z" />
                </svg>
              </InboxIconContainer>
              <AchieveIconContainer>
                <svg
                  aria-hidden="true"
                  className="svg-icon iconAchievements"
                  width="18"
                  height="18"
                  viewBox="0 0 18 18"
                  fill="#525960"
                >
                  <path d="M15 2V1H3v1H0v4c0 1.6 1.4 3 3 3v1c.4 1.5 3 2.6 5 3v2H5s-1 1.5-1 2h10c0-.4-1-2-1-2h-3v-2c2-.4 4.6-1.5 5-3V9c1.6-.2 3-1.4 3-3V2h-3ZM3 7c-.5 0-1-.5-1-1V4h1v3Zm8.4 2.5L9 8 6.6 9.4l1-2.7L5 5h3l1-2.7L10 5h2.8l-2.3 1.8 1 2.7h-.1ZM16 6c0 .5-.5 1-1 1V4h1v2Z" />
                </svg>
              </AchieveIconContainer>
              <HelpIconContainer onClick={() => handleClickMenu('help')}>
                <svg
                  aria-hidden="true"
                  className="svg-icon iconHelp"
                  width="18"
                  height="18"
                  viewBox="0 0 18 18"
                  fill="#525960"
                >
                  <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8Zm.81 12.13c-.02.71-.55 1.15-1.24 1.13-.66-.02-1.17-.49-1.15-1.2.02-.72.56-1.18 1.22-1.16.7.03 1.2.51 1.17 1.23ZM11.77 8c-.59.66-1.78 1.09-2.05 1.97a4 4 0 0 0-.09.75c0 .05-.03.16-.18.16H7.88c-.16 0-.18-.1-.18-.15.06-1.35.66-2.2 1.83-2.88.39-.29.7-.75.7-1.24.01-1.24-1.64-1.82-2.35-.72-.21.33-.18.73-.18 1.1H5.75c0-1.97 1.03-3.26 3.03-3.26 1.75 0 3.47.87 3.47 2.83 0 .57-.2 1.05-.48 1.44Z" />
                </svg>
              </HelpIconContainer>
              {clickedMenu === 'help' ? (
                <HelpDropdown>
                  <HelpDropdownSubContent>
                    <span className="name">Tour</span>
                    <span className="discription">
                      Start here for a quick overview of the site
                    </span>
                  </HelpDropdownSubContent>
                  <HelpDropdownSubContent>
                    <span className="name">Help Center</span>
                    <span className="discription">
                      Detailed answers to any questions you might have
                    </span>
                  </HelpDropdownSubContent>
                  <HelpDropdownSubContent>
                    <span className="name">Meta</span>
                    <span className="discription">
                      Discuss the workings and policies of this site
                    </span>
                  </HelpDropdownSubContent>
                  <HelpDropdownSubContent>
                    <span className="name">About Us</span>
                    <span className="discription">
                      Learn more about Stack Overflow the company
                    </span>
                  </HelpDropdownSubContent>
                  <HelpDropdownSubContent>
                    <span className="name">Business</span>
                    <span className="discription">
                      Learn more about our products
                    </span>
                  </HelpDropdownSubContent>
                </HelpDropdown>
              ) : null}
              <CommunityIconContainer
                onClick={() => handleClickMenu('community')}
              >
                <svg
                  aria-hidden="true"
                  className="svg-icon iconStackExchange"
                  width="18"
                  height="18"
                  viewBox="0 0 18 18"
                  fill="#525960"
                >
                  <path d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z" />
                </svg>
              </CommunityIconContainer>
              {clickedMenu === 'community' ? (
                <CommunityDropdown>
                  <DropdownSubTitle>CURRENT COMMUNITY</DropdownSubTitle>
                  <DropdownSubContent>
                    <span className="name">Stack Overflow</span>
                    <span
                      className="discription loginout"
                      role="presentation"
                      onClick={() => {
                        setIsLogin(false);
                        window.sessionStorage.removeItem('Authorization');
                      }}
                    >
                      Logout
                    </span>
                  </DropdownSubContent>
                  <DropdownSubTitle>
                    MORE STACK EXCHANGE COMMUNITIES
                  </DropdownSubTitle>
                  <DropdownSubContent>
                    <span className="discription">3D Printing</span>
                    <span className="discription">Academia</span>
                  </DropdownSubContent>
                </CommunityDropdown>
              ) : null}
            </>
          ) : (
            <AccountBtnsWrapper>
              <div className="login">
                <Link to="/users/login">
                  <LoginBtn
                    width="59.45px"
                    height="32px"
                    fontSize="13px"
                    fontWeight="400"
                  >
                    Log in
                  </LoginBtn>
                </Link>
              </div>
              <div className="signup">
                <Link to="/signup">
                  <SignupBtn
                    width="64.44px"
                    height="32px"
                    fontSize="13px"
                    fontWeight="400"
                  >
                    Sign up
                  </SignupBtn>
                </Link>
              </div>
            </AccountBtnsWrapper>
          )}
        </Header_Right>
      </HeaderTopbarContainer>
    </HeaderContinaer>
  );
};

export default Header;
