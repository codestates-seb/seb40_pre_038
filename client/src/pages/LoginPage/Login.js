import styled from 'styled-components';
import LoginForm from './LoginForm';

const Wrapper = styled.div`
  display: flex;
  width: 100%;
  height: calc(100vh - 50px);
  justify-content: center;
  align-items: center;
  background-color: #f1f2f3;
`;

const FlexItem = styled.div`
  @media screen and (max-width: 640px) {
    width: 267.345px;
    height: 583.469px;
    font-size: 1.8rem;
  }
  width: 288.453px;
  height: 572.531px;
  display: block;
`;

const LogoDiv = styled.div`
  text-align: center;
  font-size: 1.61538462rem;
  margin-bottom: 36px;
  margin-left: auto;
  margin-right: auto;
`;

const OauthDivWrapper = styled.div`
  width: 100%;
  display: flex;
  margin-bottom: 16px;
  margin-left: auto;
  margin-right: auto;
`;

const OauthGitHub = styled.button`
  margin-right: 0px;
  margin-left: 0px;
  margin: 4px;
  flex: 1 auto;
  background-color: #2f3337;
  color: #ffffff;

  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.8em;
  border: 1px solid transparent;
  outline: none;
  font-size: 13px;
  font-weight: normal;
  line-height: 15/13px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;

  border-color: #d6d9dc;
  border-radius: 5px;
  border-style: solid;
  border-width: 1px;
`;

const ExternalLink = styled.div`
  max-width: 97.2307692rem/4;
  width: 100%;
  text-align: center;
  font-size: 13px;
  padding: 16 0 16 0px;
  margin-bottom: 24px;
  margin-left: auto;
  margin-right: auto;
  box-sizing: inherit;
  display: block;
`;

function Login() {
  return (
    <Wrapper>
      <FlexItem>
        <LogoDiv>
          <a href="https://stackoverflow.com">
            <svg
              aria-hidden="true"
              className="native svg-icon iconLogoGlyphMd"
              width="32"
              height="37"
              viewBox="0 0 32 37"
            >
              <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
              <path
                d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                fill="#F48024"
              ></path>
            </svg>
          </a>
        </LogoDiv>
        <OauthDivWrapper>
          <OauthGitHub>
            <svg
              aria-hidden="true"
              className="svg-icon iconGitHub"
              width="18"
              height="18"
              viewBox="0 0 18 18"
            >
              <path
                d="M9 1a8 8 0 0 0-2.53 15.59c.4.07.55-.17.55-.38l-.01-1.49c-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.42 7.42 0 0 1 4 0c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48l-.01 2.2c0 .21.15.46.55.38A8.01 8.01 0 0 0 9 1Z"
                fill="#ffffff"
              ></path>
            </svg>
            <span style={{ marginLeft: '5px' }}>Log in with GitHub</span>
          </OauthGitHub>
        </OauthDivWrapper>
        <LoginForm></LoginForm>
        <ExternalLink>
          {` Don't have an account? `}
          <a href="https://stackoverflow.com/users/signup?ssrc=head">Sign up</a>
        </ExternalLink>
      </FlexItem>
    </Wrapper>
  );
}

export default Login;
