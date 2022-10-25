import styled from 'styled-components';

const HeaderContinaer = styled.div`
    width: 100%;
    height: 47px;
    border-top: 3px solid #F48225;
    background-color: #F8F9F9;
`;

const LogoContainer = styled.div`
    padding: 0px 8px;
    .logo-img {
        width: 150px;
        height: 30px;
        background-image: url(https://cdn.sstatic.net/Img/unified/sprites.svg?v=fcc0ea44ba27);
        background-position-x: 0px;
        background-position-y: -500px;
        border-image-slice: 100%;
    }
`

const Header = () => {
    return (
        <HeaderContinaer>
            <LogoContainer>
                <a href='https://stackoverflow.com/'><div className='logo-img'></div></a>
            </LogoContainer>
        </HeaderContinaer>
    )
}

export default Header;