import styled from 'styled-components';
import SignUpForm from './SignUpForm';

const Content = styled.div`
  width: 100%;
  height: calc(100vh - 50px);
  margin: 0;
  background-color: transparent;
  border-left: 0;
  border-right: 0;
  border-radius: 0;
  border: 1px solid #d6d9dc;
  border-top-width: 0;
  border-block-width: 0;
  padding: 24;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f1f2f3;

  @media screen and (max-width: 816px) {
    padding-left: 16;
    padding-right: 16;
  }
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;

  @media screen and (max-width: 640px) {
    flex-direction: column;
  }
`;

const NoticeWrapper = styled.div`
  max-width: calc(97.2307692rem / 3);
  font-size: 1.15384615rem;
  margin-bottom: 128px;
  margin-right: 48px;

  @media (max-width: 816px) {
    display: none;
  }
`;

const NoticeTitle = styled.h1`
  line-height: 1;
  font-size: 2.07692308rem;
  margin-bottom: 32px;
  margin: 0 0 1em;
`;

const NoticeContent = styled.div`
  display: flex;
  margin-bottom: 24px;
`;

const NoticeContentImg = styled.div`
  margin-right: 8px;
  color: #0a95ff;
`;

const NoticeFooter = styled.div`
  color: #6a737c;
  font-size: 13px;
`;

const FormWrapper = styled.div`
  flex-shrink: 0 !important;
`;

const FormTitle = styled.div`
  max-width: calc(97.2307692rem / 3);
  display: none;
  text-align: center;
  font-size: 1.61538462rem;
  margin-bottom: 24px;
  margin-left: auto;
  margin-right: auto;

  @media (max-width: 816px) {
    display: block;
  }

  @media (max-width: 640px) {
    font-size: 1.8rem;
  }
`;

const OauthDivWrapper = styled.div`
  max-width: calc(97.2307692rem / 3);
  display: flex;
  margin-bottom: 16px;
  flex-direction: column;
  flex: 1 auto;
  box-sizing: inherit;
  width: 300px;
  margin-left: auto;
  margin-right: auto;

  @media (max-width: 816px) {
    margin-left: 52.656;
    margin-right: 52.656;
  }
`;

const OauthGitHub = styled.button`
  margin-right: 0px;
  margin-left: 0px;
  margin: 4px;
  flex: 1 auto;
  background-color: #2f3337;
  color: #ffffff;

  position: relative;
  display: inline-block;
  padding: 0.8em;
  border: 1px solid transparent;
  outline: none;
  font-size: 13px;
  font-weight: normal;
  line-height: calc(15 / 13);
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  user-select: none;

  border-color: #d6d9dc;
  border-radius: 5px;
  border-style: solid;
  border-width: 1px;
  box-sizing: inherit;
`;

function Signup() {
  return (
    <Content>
      <Wrapper>
        <NoticeWrapper>
          <NoticeTitle>Join the Stack Overflow community</NoticeTitle>
          <NoticeContent>
            <NoticeContentImg>
              <svg
                width="26"
                height="26"
                className="svg-icon mtn2"
                style={{
                  verticalAlign: 'Bottom',
                  marginTop: '-2px',
                }}
              >
                <path
                  opacity=".5"
                  d="M4.2 4H22a2 2 0 012 2v11.8a3 3 0 002-2.8V5a3 3 0 00-3-3H7a3 3 0 00-2.8 2z"
                  fill="#0a95ff"
                ></path>
                <path
                  d="M1 7c0-1.1.9-2 2-2h18a2 2 0 012 2v12a2 2 0 01-2 2h-2v5l-5-5H3a2 2 0 01-2-2V7zm10.6 11.3c.7 0 1.2-.5 1.2-1.2s-.5-1.2-1.2-1.2c-.6 0-1.2.4-1.2 1.2 0 .7.5 1.1 1.2 1.2zm2.2-5.4l1-.9c.3-.4.4-.9.4-1.4 0-1-.3-1.7-1-2.2-.6-.5-1.4-.7-2.4-.7-.8 0-1.4.2-2 .5-.7.5-1 1.4-1 2.8h1.9v-.1c0-.4 0-.7.2-1 .2-.4.5-.6 1-.6s.8.1 1 .4a1.3 1.3 0 010 1.8l-.4.3-1.4 1.3c-.3.4-.4 1-.4 1.6 0 0 0 .2.2.2h1.5c.2 0 .2-.1.2-.2l.1-.7.5-.7.6-.4z"
                  fill="#0a95ff"
                ></path>
              </svg>
            </NoticeContentImg>
            <div>Get unstuck - ask a question</div>
          </NoticeContent>
          <NoticeContent>
            <NoticeContentImg>
              <svg width="26" height="26" className="svg-icon mtn2">
                <path
                  d="M12 .7a2 2 0 013 0l8.5 9.6a1 1 0 01-.7 1.7H4.2a1 1 0 01-.7-1.7L12 .7z"
                  fill="#0a95ff"
                ></path>
                <path
                  opacity=".5"
                  d="M20.6 16H6.4l7.1 8 7-8zM15 25.3a2 2 0 01-3 0l-8.5-9.6a1 1 0 01.7-1.7h18.6a1 1 0 01.7 1.7L15 25.3z"
                  fill="#0a95ff"
                ></path>
              </svg>
            </NoticeContentImg>
            <div>Unlock new privileges like voting and commenting</div>
          </NoticeContent>
          <NoticeContent>
            <NoticeContentImg>
              <svg width="26" height="26" className="svg-icon mtn2">
                <path
                  d="M14.8 3a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8l8.2 8.2c.8.8 2 .8 2.8 0l10-10c.4-.4.6-.9.6-1.4V5a2 2 0 00-2-2h-8.2zm5.2 7a2 2 0 110-4 2 2 0 010 4z"
                  fill="#0a95ff"
                ></path>
                <path
                  opacity=".5"
                  fill="#0a95ff"
                  d="M13 0a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8c.1-.2.3-.6.6-.8l10-10a2 2 0 011.4-.6h9.6a2 2 0 00-2-2H13z"
                ></path>
              </svg>
            </NoticeContentImg>
            <div>Save your favorite tags, filters, and jobs</div>
          </NoticeContent>
          <NoticeContent>
            <NoticeContentImg>
              <svg width="26" height="26" className="svg-icon mtn2">
                <path
                  fill="#0a95ff"
                  d="M21 4V2H5v2H1v5c0 2 2 4 4 4v1c0 2.5 3 4 7 4v3H7s-1.2 2.3-1.2 3h14.4c0-.6-1.2-3-1.2-3h-5v-3c4 0 7-1.5 7-4v-1c2 0 4-2 4-4V4h-4zM5 11c-1 0-2-1-2-2V6h2v5zm11.5 2.7l-3.5-2-3.5 1.9L11 9.8 7.2 7.5h4.4L13 3.8l1.4 3.7h4L15.3 10l1.4 3.7h-.1zM23 9c0 1-1 2-2 2V6h2v3z"
                ></path>
              </svg>
            </NoticeContentImg>
            <div>Earn reputation and badges</div>
          </NoticeContent>
          <NoticeFooter>
            Collaborate and share knowledge with a private group for FREE.
            <br></br>
            <a
              href="https://stackoverflow.com/teams?utm_source=so-owned&amp;utm_medium=product&amp;utm_campaign=free-50&amp;utm_content=public-sign-up"
              target="blank"
            >
              Get Stack Overflow for Teams free for up to 50 users
            </a>
          </NoticeFooter>
        </NoticeWrapper>
        <FormWrapper>
          <FormTitle>
            Create your Stack Overflow account. It’s free and only takes a
            minute.
          </FormTitle>
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
              <span style={{ marginLeft: '5px', fontWeight: 'bold' }}>
                Sign up with GitHub
              </span>
            </OauthGitHub>
          </OauthDivWrapper>
          <SignUpForm></SignUpForm>
        </FormWrapper>
      </Wrapper>
    </Content>
  );
}

export default Signup;
