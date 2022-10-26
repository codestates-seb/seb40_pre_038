import styled from 'styled-components';

const FooterContainer = styled.div`
  width: 100%;
  height: 322px;
  background-color: #232627;
  position: fixed;
  bottom: 0;
  padding: 32px 12px 12px;
  display: flex;

  @media screen and (min-width: 641px) {
    //641 ~ 980px
    height: 394.88px;
    padding: 16px;
    flex-direction: column;
  }
  @media screen and (min-width: 981px) {
    //981px ~
    justify-content: center;
  }
`;

const FooterLogoContainer = styled.div`
  width: 64px;
  height: 244px;
  margin-bottom: 32px;
`;

const SiteFooterContainer = styled.div`
  height: 254px;
  padding: 0 12px 24px 0;
  display: flex;
  flex-direction: column;
`;

const SiteFooterTitleContainer = styled.div`
  font-size: 13px;
  color: #babfc4;
  font-weight: 700;
  margin-bottom: 12px;
  &:hover {
    cursor: pointer;
  }
`;

const SiteFooters = styled.div`
  display: flex;

  @media screen and (min-width: 641px) {
    //641 ~ 980px
    flex-direction: column;
  }
  @media screen and (min-width: 981px) {
    //981px ~
    flex-direction: row;
  }
`;

const SiteFooterLists = styled.div`
  display: flex;
  @media screen and (min-width: 641px) {
    //641 ~ 980px
    flex-direction: row;
  }
  @media screen and (min-width: 981px) {
    //981px ~
    flex-direction: column;
  }
`;

const SiteFooterListContainer = styled.div`
  font-size: 13px;
  color: #9199a1;
  font-weight: 600;
  padding: 4px 0;
  &:hover {
    cursor: pointer;
    color: #9fa6ad;
  }
`;

const StackFooterContainer = styled(SiteFooterContainer)`
  width: 184.719px;
`;
const ProductsFooterContainer = styled(SiteFooterContainer)`
  width: 137.188px;
`;
const CompanyFooterContainer = styled(SiteFooterContainer)`
  width: 165.141px;
`;
const ExchangeFooterContainer = styled(SiteFooterContainer)`
  width: 255.172px;
`;

const CopyrightFooterContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 313.094px;
  font-weight: 600;
`;

const SocialListContainer = styled.ul`
  list-style: none;
  display: flex;
  li {
    color: #9199a1;
    font-size: 11px;
    padding: 4px 4px;
    &:hover {
      cursor: pointer;
      color: #9fa6ad;
    }
  }
`;

const CopyrightInfoContainer = styled.p`
  font-size: 11px;
  color: #9199a1;
  margin: 188.5px 0 24px 0;
  a {
    color: #9199a1;
  }
`;

const Footer = () => {
  return (
    <FooterContainer>
      <FooterLogoContainer>
        <svg
          aria-hidden="true"
          className="native svg-icon iconLogoGlyphMd"
          width="32"
          height="37"
          viewBox="0 0 32 37"
        >
          <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB" />
          <path
            d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
            fill="#F48024"
          />
        </svg>
      </FooterLogoContainer>
      <SiteFooters>
        <StackFooterContainer>
          <SiteFooterTitleContainer>STACK OVERFLOW</SiteFooterTitleContainer>
          <SiteFooterLists>
            <SiteFooterListContainer>Questions</SiteFooterListContainer>
            <SiteFooterListContainer>Help</SiteFooterListContainer>
          </SiteFooterLists>
        </StackFooterContainer>
        <ProductsFooterContainer>
          <SiteFooterTitleContainer>PRODUCTS</SiteFooterTitleContainer>
          <SiteFooterLists>
            <SiteFooterListContainer>Teams</SiteFooterListContainer>
            <SiteFooterListContainer>Advertising</SiteFooterListContainer>
            <SiteFooterListContainer>Collectives</SiteFooterListContainer>
            <SiteFooterListContainer>Talent</SiteFooterListContainer>
          </SiteFooterLists>
        </ProductsFooterContainer>
        <CompanyFooterContainer>
          <SiteFooterTitleContainer>COMPANY</SiteFooterTitleContainer>
          <SiteFooterLists>
            <SiteFooterListContainer>About</SiteFooterListContainer>
            <SiteFooterListContainer>Press</SiteFooterListContainer>
            <SiteFooterListContainer>Work Here</SiteFooterListContainer>
            <SiteFooterListContainer>Legal</SiteFooterListContainer>
            <SiteFooterListContainer>Privacy Policy</SiteFooterListContainer>
            <SiteFooterListContainer>Terms of Service</SiteFooterListContainer>
            <SiteFooterListContainer>Contact Us</SiteFooterListContainer>
            <SiteFooterListContainer>Cookie Settings</SiteFooterListContainer>
            <SiteFooterListContainer>Cookie Policy</SiteFooterListContainer>
          </SiteFooterLists>
        </CompanyFooterContainer>
        <ExchangeFooterContainer>
          <SiteFooterTitleContainer>Technology</SiteFooterTitleContainer>
          <SiteFooterLists>
            <SiteFooterListContainer>
              Culture & recreation
            </SiteFooterListContainer>
            <SiteFooterListContainer>Life & arts</SiteFooterListContainer>
            <SiteFooterListContainer>Science</SiteFooterListContainer>
            <SiteFooterListContainer>Professional</SiteFooterListContainer>
            <SiteFooterListContainer>Business</SiteFooterListContainer>
            <SiteFooterListContainer>API</SiteFooterListContainer>
            <SiteFooterListContainer>Data</SiteFooterListContainer>
          </SiteFooterLists>
        </ExchangeFooterContainer>
      </SiteFooters>
      <CopyrightFooterContainer>
        <SocialListContainer>
          <li>Blog</li>
          <li>Facebook</li>
          <li>Twitter</li>
          <li>LinkedIn</li>
          <li>Instagram</li>
        </SocialListContainer>
        <CopyrightInfoContainer>
          Site design / logo © 2022 Stack Exchange Inc; user contributions
          licensed under{' '}
          <u>
            <a href="/">CC BY-SA</a>
          </u>
          . rev 2022.10.25.33519
        </CopyrightInfoContainer>
      </CopyrightFooterContainer>
    </FooterContainer>
  );
};

export default Footer;
