import styled from 'styled-components';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Popup from '../../components/Modal';
import { SIGNUP_URL } from '../../api/requests';

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

function SignUpForm() {
  const [emessage, setEmessage] = useState(''); // email
  const [pmessage, setPmessage] = useState(''); // password
  const [nmessage, setNmessage] = useState(''); // display name
  const [error, setError] = useState(null); // email error message state
  const [perror, setPerror] = useState(null); // password error message state
  const [popup, setPopup] = useState({
    open: false,
    title: '',
    message: '',
    callback: false,
  }); // modal state

  const navigate = useNavigate(); // 얘는 react가 아닌 react-router-dom에서 import해야함

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

  const handleNchange = (event) => {
    setNmessage(event.target.value);
  }; // Display name 저장

  const handleEchange = (event) => {
    if (!isValidEmail(event.target.value)) {
      setError(`Not a valid email address.`);
    } else {
      setError(null);
    }
    setEmessage(event.target.value);
  }; // 올바른 형식일 때 email 저장

  const handlePchange = (event) => {
    if (!isValidPassword(event.target.value)) {
      setPerror(`Passwords must contain at least eight characters.`);
    } else {
      setPerror(null);
    }
    setPmessage(event.target.value);
  }; // 올바른 형식일 때 password 저장

  const handleSubmitButton = (e) => {
    e.preventDefault(); // 새로고침 방지
    if (emessage === '') {
      setPopup({
        open: true,
        title: 'Register denied',
        message: 'Please input your email address.',
      }); // 이메일 공란이면 모달창
    } else if (pmessage === '') {
      setPopup({
        open: true,
        title: 'Register denied',
        message: 'Please input your password.',
      }); // 패스워드 공란이면 입력창
    } else if (error !== null || perror !== null) {
      setPopup({
        open: true,
        title: 'Register denied',
        message: 'Invailed email or password. Please try again.',
      }); // 이메일이랑 패스워드가 입력은 되어 있는데, 둘 중 하나라도 올바른 형식이 아닐 경우 모달창
    } else {
      signupSubmit(); // 전부 만족 시 axios 실행
    }
  };

  const signupSubmit = async () => {
    await axios
      .post(
        SIGNUP_URL,
        {
          nickName: nmessage,
          email: emessage,
          password: pmessage,
        },
        { 'Content-type': 'application/json' } // CORS error solve
      )
      .then((res) => {
        if (res.status === 201) {
          // 201이면 성공했다고 하고 로그인 화면으로 이동
          setPopup({
            open: true,
            title: 'Register confirmed',
            message:
              'Your account has been successfully confirmed. Please log in now.',
            callback: function () {
              navigate('/login');
            },
          });
        }
      })
      .catch(() => {
        setPopup({
          open: true,
          title: 'Register denied',
          message:
            'Your Display name or email is already in use. Please try other name/email.',
          //     });
        });
        // .catch((err) => {
        //   console.log(err);
        //   console.log(err.status);
        //   if (err.status === 500) {
        //     //500으로 오면 닉네임이 중복되었습니다 라고 하고
        //     setPopup({
        //       open: true,
        //       title: 'Register denied',
        //       message:
        //         'Your Display name is already in use. Please try other name.',
        //       callback: function () {
        //         navigate('/');
        //       },
        //     });
        //   } else if (err.status === 409) {
        //     //409로 오면 이메일이 중복되었습니다 라고 모달 출력
        //     setPopup({
        //       open: true,
        //       title: 'Register denied',
        //       message: 'Your Email is already in use. Please try other email.',
        //       callback: function () {
        //         navigate('/');
        //       },
        //     });
        //   }
        // });
        //! 에러코드 받아서 그거에 따라 따로 모달을 띄우고 싶었는데, 이유는 모르겠고 에러 스테이터스를 못받아와서 일단 에러는 통일
      });
  };

  return (
    <FormContainer>
      <LoginForm>
        <Popup
          open={popup.open}
          setPopup={setPopup}
          message={popup.message}
          title={popup.title}
          callback={popup.callback}
        />
        <FormContent>
          <ContentLabel htmlFor="displayName">Display name</ContentLabel>
          <InputDiv>
            <InputContent
              id="displayName"
              value={nmessage}
              onChange={handleNchange}
            ></InputContent>
          </InputDiv>
        </FormContent>
        <FormContent>
          <ContentLabel htmlFor="email">Email</ContentLabel>
          <InputDiv>
            <InputContent
              id="email"
              name="email"
              value={emessage}
              onChange={handleEchange}
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
            {'Passwords must contain at least eight characters or numbers.'}
          </PasswordCaption>
          <ButtonBlue width={'100%'} onClick={handleSubmitButton}>
            Sign up
          </ButtonBlue>
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
