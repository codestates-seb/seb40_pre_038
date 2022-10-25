import styled from 'styled-components';
import Searchbar from './Searchbar';

const HeaderContinaer = styled.div`
  width: 100%;
  height: 50px;
  border-top: 3px solid #f48225;
  background-color: #f8f9f9;
  display: flex;
  align-items: center;
`;

const LogoContainer = styled.div`
  height: 100%;
  display: flex;
  align-items: center;
  padding: 0px 8px;

  .logo-img {
    width: 150px;
    height: 30px;
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
    }
  }
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
      <Searchbar placeholder={'Search...'}></Searchbar>
    </HeaderContinaer>
  );
};

export default Header;
