import styled from 'styled-components';
import { ButtonBlue } from '../../components/Buttons';
import { useState } from 'react';

const FormContainer = styled.div`
  max-width: calc(97.2307692rem / 4);
  box-shadow: 0 10px 24px rgba(0, 0, 0, 0.05), 0 20px 48px rgba(0, 0, 0, 0.05),
    0 1px 4px rgba(0, 0, 0, 0.1);
  padding: 24px;
  margin-bottom: 24px;
  margin-left: auto;
  margin-right: auto;
  background-color: #ffffff;
  border-radius: 7px;
  width: 100%;
`;

const LoginAreaForm = styled.form`
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

function LoginForm() {
  const [message, setMessage] = useState('');
  const [pmessage, setPmessage] = useState('');
  const [error, setError] = useState(null);
  const [perror, setPerror] = useState(null);

  function isValidEmail(email) {
    if (email.length === 0) {
      return false;
    }
    return true;
  }

  function isValidPassword(password) {
    if (password.length === 0) {
      return false;
    }
    return true;
  }

  const handleChange = (event) => {
    if (!isValidEmail(event.target.value)) {
      setError(`Email cannot be empty.`);
    } else {
      setError(null);
    }
    setMessage(event.target.value);
  };

  const handlePchange = (event) => {
    console.log(isValidPassword(event.target.value));
    if (!isValidPassword(event.target.value)) {
      setPerror(`Passwords cannot be empty.`);
    } else {
      setPerror(null);
    }
    setPmessage(event.target.value);
  };

  return (
    <FormContainer>
      <LoginAreaForm>
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
        </FormContent>
        <FormContent>
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
        </FormContent>
        <div style={{ margin: '6px' }}>
          <ButtonBlue width={'100%'}>Log in</ButtonBlue>
        </div>
      </LoginAreaForm>
    </FormContainer>
  );
}

export default LoginForm;
