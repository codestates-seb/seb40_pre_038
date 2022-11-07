import styled from 'styled-components';
import { useState } from 'react';
import axios from '../../api/axios';
// import { useNavigate } from 'react-router-dom';
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

  // const navigate = useNavigate();

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
      }) // 받은 이메일과 패스워드로 로그인 POST 요청
      .then((response) => {
        // console.log(response.status);
        // console.log(`로그인 성공`);
        let accessToken = response.headers.get('Authorization'); // Access token
        // let refreshToken = response.headers.get('Refresh'); // Refresh token
        // console.log(`액세스 토큰 : ${accessToken}`);
        // console.log(`리프레쉬 토큰 : ${refreshToken}`);
        sessionStorage.setItem('Authorization', accessToken); // 없으면 Access 토큰 로컬스토리지에 저장
        // navigate('/', { replace: true }); // 메인페이지로 옮기기
        window.location.replace('/');
      })
      .catch((e) => {
        console.log(`ERROR RESPONSE : ${e.status}`);
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
