import styled from 'styled-components';
import Searchbar from './Searchbar';
//import user_icon_img from '../icons/user_icon.png';

const HeaderContinaer = styled.div`
  width: 100%;
  height: 50px;
  border-top: 3px solid #f48225;
  background-color: #f8f9f9;
  display: flex;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
  position: fixed;
`;

const LogoContainer = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0px 8px;

  .logo-img {
    width: 150px;
    height: 30px;
    padding-bottom: 3px;
    background-image: url(images/sprites_img.svg);
    background-position-x: 0px;
    background-position-y: -500px;
    border-image-slice: 100%;
  }

  &:hover {
    background-color: #e3e5e8;
  }
`;

const ProductsBtnContainer = styled.div`
  width: 82px;
  height: 33px;
  margin: 0 2px;
  padding-top: 5px;

  .product_btn {
    width: 78px;
    height: 29px;
    background-color: #f8f9f9;
    color: #525960;
    font-size: 13px;
    font-weight: 400;
    border: none;
    border-radius: 20px;

    &:hover {
      background-color: #e3e5e8;
      color: black;
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
`;

const HeaderIcon = styled.div`
  width: 20px;
  height: 47px;
  padding: 0 10px;
  display: flex;
  align-items: center;
  justify-content: center;

  &:hover {
    background-color: #e3e5e8;
    svg {
      fill: black;
    }
  }
`;

const InboxIconContainer = styled(HeaderIcon)``;
const AchiveIconContainer = styled(HeaderIcon)``;
const HelpIconContainer = styled(HeaderIcon)``;
const CommunityIconContainer = styled(HeaderIcon)`
  padding-bottom: 3px;
  height: 45px;
`;

const Header = () => {
  return (
    <HeaderContinaer>
      <LogoContainer>
        <a href="https://stackoverflow.com/">
          <div className="logo-img"></div>
        </a>
      </LogoContainer>
      <ProductsBtnContainer>
        <button className="product_btn">Products</button>
      </ProductsBtnContainer>
      <Searchbar placeholder={'Search...'} width={'200px'}></Searchbar>
      <UserIconConatiner>
        <a href="/">
          <UserProfileIcon></UserProfileIcon>
        </a>
        <UserInfo>
          <span className="user_reputation">1</span>
          <div className="user_badges">
            <span className="dot">•</span>
            <span className="count">1</span>
          </div>
        </UserInfo>
      </UserIconConatiner>
      <InboxIconContainer>
        <a href="/">
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
        </a>
      </InboxIconContainer>
      <AchiveIconContainer>
        <a href="/">
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
        </a>
      </AchiveIconContainer>
      <HelpIconContainer>
        <a href="/">
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
        </a>
      </HelpIconContainer>
      <CommunityIconContainer>
        <a href="/">
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
        </a>
      </CommunityIconContainer>
    </HeaderContinaer>
  );
};

export default Header;
