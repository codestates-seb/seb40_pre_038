import styled from 'styled-components';
import { useState } from 'react';
import { ButtonBlue } from '../../components/Buttons';

const FormContainer = styled.div`
  max-width: calc(97.2307692rem / 3);
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  padding: 24px;
  margin-bottom: 24px;
  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
  border-radius: 7px;
  width: 300px;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  margin-left: 0;
  margin-right: 0;
  margin: calc(12 / 2 * -1);
`;

const FormContent = styled.div`
  margin-left: 0;
  margin-right: 0;
  margin: 6px;
  display: flex;
  flex-direction: column;
`;

const ContentLabel = styled.label`
  margin-left: 0;
  margin-right: 0;
  margin: 2px;
  cursor: pointer;
  color: #0c0d0e;
  font-family: inherit;
  font-size: 1.15384615rem;
  font-weight: 600;
  padding: 0 2px;
`;

const InputDiv = styled.div`
  margin-left: 0;
  margin-right: 0;
  margin: 2px;
  position: relative;
  display: flex;
`;

const InputContent = styled.input`
  width: 100%;
  margin: 0;
  padding: 0.6em 0.7em;
  border: 1px solid #babfc4;
  border-radius: 3px;
  color: #0c0d0e;
  font-size: 13px;
  font-family: inherit;
`;

const ErrorMessageP = styled.p`
  margin-left: 0;
  margin-right: 0;
  margin: 2px;
  color: #de4f54;
  padding: 2px;
  font-size: 12px;
`;

const PasswordCaption = styled.p`
  color: #6a737c;
  font-style: 12px;
  margin-bottom: 4px;
  margin-top: 4px;
`;

const PasswordFormFooter = styled.div`
  text-align: left;
  color: #6a737c;
  font-size: 12px;
  margin-top: 32px;
`;

function SignUpForm() {
  const [message, setMessage] = useState('');
  const [pmessage, setPmessage] = useState('');
  const [error, setError] = useState(null);
  const [perror, setPerror] = useState(null);

  function isValidEmail(email) {
    return /\S+@\S+\.\S+/.test(email);
  }

  function isValidPassword(password) {
    if (password.length >= 8) {
      return true;
    }
    return false;
  }
  //삼항연산자 쓰면 안됨

  const handleChange = (event) => {
    if (!isValidEmail(event.target.value)) {
      setError(`Not a valid email address.`);
    } else {
      setError(null);
    }
    setMessage(event.target.value);
  };

  const handlePchange = (event) => {
    console.log(isValidPassword(event.target.value));
    if (!isValidPassword(event.target.value)) {
      setPerror(`Passwords must contain at least eight characters.`);
    } else {
      setPerror(null);
    }
    setPmessage(event.target.value);
  };

  return (
    <FormContainer>
      <LoginForm>
        <FormContent>
          <ContentLabel for="displayName">Display name</ContentLabel>
          <InputDiv>
            <InputContent id="displayName"></InputContent>
          </InputDiv>
        </FormContent>
        <FormContent>
          <ContentLabel htmlFor="email">Email</ContentLabel>
          <InputDiv>
            <InputContent
              id="email"
              name="email"
              value={message}
              onChange={handleChange}
            ></InputContent>
            {error && (
              <svg
                aria-hidden="true"
                width="18"
                height="18"
                viewBox="0 0 18 18"
                fill="#DE4F54"
                style={{ position: 'absolute', right: '5px', marginTop: '7px' }}
              >
                <path d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"></path>
              </svg>
            )}
          </InputDiv>
          {error && <ErrorMessageP>{error}</ErrorMessageP>}
          <ContentLabel htmlFor="password">Password</ContentLabel>
          <InputDiv>
            <InputContent
              id="password"
              name="password"
              type="password"
              value={pmessage}
              onChange={handlePchange}
            ></InputContent>
            {perror && (
              <svg
                aria-hidden="true"
                width="18"
                height="18"
                viewBox="0 0 18 18"
                fill="#DE4F54"
                style={{ position: 'absolute', right: '5px', marginTop: '7px' }}
              >
                <path d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"></path>
              </svg>
            )}
          </InputDiv>
          {perror && <ErrorMessageP>{perror}</ErrorMessageP>}
          <PasswordCaption>
            {
              'Passwords must contain at least eight characters, including at least 1 letter and 1 number.'
            }
          </PasswordCaption>
          <ButtonBlue width={'100%'}>Sign up</ButtonBlue>
          <PasswordFormFooter>
            {'By clicking “Sign up”, you agree to our '}
            <a
              href="https://stackoverflow.com/legal/terms-of-service/public"
              name="tos"
              target="blank"
            >
              terms of service
            </a>
            {', '}
            <a
              href="https://stackoverflow.com/legal/privacy-policy"
              name="privacy"
              target="blank"
            >
              privacy policy
            </a>
            {' and '}
            <a
              href="https://stackoverflow.com/legal/cookie-policy"
              name="cookie"
              target="blank"
            >
              cookie policy
            </a>
          </PasswordFormFooter>
        </FormContent>
      </LoginForm>
    </FormContainer>
  );
}

export default SignUpForm;
