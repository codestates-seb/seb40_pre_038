import styled from 'styled-components';
import { useState } from 'react';
import axios from 'axios';
import Popup from '../../components/Modal';
import { LOGIN_URL } from '../../api/requests';

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

const ButtonBlue = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  background: #0995ff;
  color: #ffffff;
  font-weight: bold;
  border: 1px solid #0995ff;
  border-radius: 4px;
  cursor: pointer;
  box-shadow: inset 0 1px 0 0 #ffffff;
  width: ${(props) => props.width || '100px'};
  height: ${(props) => props.height || '40px'};
  font-size: ${(props) => props.fontSize || '14px'};
  font-weight: ${(props) => props.fontWeight || '700'};
  :hover {
    background: #0063bf;
  }
`;

function LoginForm() {
  const [emessage, setEmessage] = useState('');
  const [pmessage, setPmessage] = useState('');
  const [error, setError] = useState(null);
  const [perror, setPerror] = useState(null);
  const [popup, setPopup] = useState({
    open: false,
    title: '',
    message: '',
    callback: false,
  }); // modal state

  /*
Logic : API는 POST api/login(수정 전)으로,
토큰은 Header에 각각 key: Authorization, Refresh / Value: {Access Token}, {Refresh Token} 형식으로 담깁니다.

현재까지 예상되는 로직은
첫 로그인 : axios를 사용해 Access Token과 Refresh Token을 요청받습니다.
이후 Refresh Token은 Local Storage에 저장합니다.
이후 로그인: 저장된 Refresh Token을 header에 담아 Access Token을 요청합니다.

Refresh Token이 만료될 시에, 재발급 API에 Refresh Token을 담아서 요청합니다.
재발급 API가 따로 존재하지 않을 경우엔 다시 첫 로그인을 반복합니다.
  */

  function isValidEmail(email) {
    if (email.length === 0) {
      return false;
    }
    return true;
  }

  function isValidEmailForm(email) {
    return /\S+@\S+\.\S+/.test(email);
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
    setEmessage(event.target.value);
  };

  const handlePchange = (event) => {
    if (!isValidPassword(event.target.value)) {
      setPerror(`Passwords cannot be empty.`);
    } else {
      setPerror(null);
    }
    setPmessage(event.target.value);
  };

  const handleLoginButton = (e) => {
    e.preventDefault();
    if (emessage === '') {
      setPopup({
        open: true,
        title: 'Error',
        message: 'Please input your email address.',
      });
    } else if (pmessage === '') {
      setPopup({
        open: true,
        title: 'Error',
        message: 'Please input your password.',
      });
    } else if (!isValidEmailForm(emessage)) {
      setPopup({
        open: true,
        title: 'Error',
        message: 'The email is not a valid email address.',
      });
    } else {
      requestLogin();
    }
  };

  const requestLogin = async () => {
    return await axios
      .post(LOGIN_URL, {
        email: emessage,
        password: pmessage,
      })
      .then((response) => {
        /// token이 필요한 API 요청 시 header Authorization에 token 담아서 보내기
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${response.data}`;
        console.log(`POST RESPONSE : ${response.data}`);
        return response.data;
      })
      .catch((e) => {
        console.log(`ERROR RESPONSE : ${e.response.data}`);
        return 'Email or password is incorrect.';
      });
  };

  return (
    <FormContainer>
      <Popup
        open={popup.open}
        setPopup={setPopup}
        message={popup.message}
        title={popup.title}
        callback={popup.callback}
      />
      <LoginAreaForm>
        <FormContent>
          <ContentLabel htmlFor="email">Email</ContentLabel>
          <InputDiv>
            <InputContent
              id="email"
              name="email"
              value={emessage}
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
          <ButtonBlue width={'100%'} onClick={handleLoginButton}>
            Log in
          </ButtonBlue>
        </div>
      </LoginAreaForm>
    </FormContainer>
  );
}

export default LoginForm;
