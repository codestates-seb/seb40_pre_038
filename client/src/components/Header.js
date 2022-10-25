import styled from 'styled-components';
import Searchbar from './Searchbar';

const HeaderContinaer = styled.div`
    width: 100%;
    height: 50px;
    border-top: 3px solid #F48225;
    background-color: #F8F9F9;
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
        background-image: url(https://cdn.sstatic.net/Img/unified/sprites.svg?v=fcc0ea44ba27);
        background-position-x: 0px;
        background-position-y: -500px;
        border-image-slice: 100%;
    }

    &:hover {
        background-color: #E3E5E8;
    }
`

const ProductsBtnContainer = styled.div`
    width: 82px;
    height: 33px;
    margin: 0 2px;

    .product_btn {
        width: 78px;
        height: 29px;
        background-color: #F8F9F9;
        color: #525960;
        font-size: 13px;
        font-weight: 400;
        border: none;
        border-radius: 20px;

        &:hover {
            background-color: #E3E5E8;
        }
    }
`

const Header = () => {
    return (
        <HeaderContinaer>
            <LogoContainer>
                <a href='https://stackoverflow.com/'><div className='logo-img'></div></a>
            </LogoContainer>
            <ProductsBtnContainer>
                <button className='product_btn'>Products</button>
            </ProductsBtnContainer>
            <Searchbar></Searchbar>
        </HeaderContinaer>
    )
}

export default Header;