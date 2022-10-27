import styled from 'styled-components';
import { ButtonBlue } from '../components/Buttons';

const HeaderWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  h1 {
    font-size: 22px;
    font-weight: bold;
    @media screen and (min-width: 641px) {
      font-size: 27px;
    }
  }
  img {
    display: none;
    width: 600px;
    @media screen and (min-width: 1050px) {
      display: block;
    }
  }
`;

const AskPageHeader = () => {
  return (
    <HeaderWrapper>
      <h1>Ask a public question</h1>
      <img
        src="https://cdn.sstatic.net/Img/ask/background.svg?v=c56910988bdf"
        alt="question mark robot"
      />
    </HeaderWrapper>
  );
};

const DecsGoodQuestionWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: #ebf4fb;
  color: #232629;
  margin: 10px 0px;
  padding: 24px;
  border: 0.5px solid #379fef;
  border-radius: 3px;
  h2 {
    font-size: 20px;
    font-weight: 500;
  }
  p {
    font-size: 15px;
    font-weight: 400;
  }
  h5 {
    font-size: 14px;
    font-weight: 600;
    margin: 10px 0px;
  }
  li {
    list-style: disc;
    margin-left: 30px;
  }
`;

const DecsGoodQuestion = () => {
  return (
    <DecsGoodQuestionWrapper>
      <h2>Writing a good question</h2>
      <p>
        You’re ready to{' '}
        <a href="https://stackoverflow.com/help/how-to-ask">ask</a> a{' '}
        <a href="https://stackoverflow.com/help/on-topic">
          programming-related question
        </a>{' '}
        and this form will help guide you through the process.
      </p>
      <p>
        Looking to ask a non-programming question? See{' '}
        <a href="https://stackexchange.com/sites#technology">the topics here</a>{' '}
        to find a relevant site.
      </p>
      <h5>Steps</h5>
      <ul>
        <li>Summarize your problem in a one-line title.</li>
        <li>Describe your problem in more detail.</li>
        <li>Describe what you tried and what you expected to happen.</li>
        <li>
          Add “tags” which help surface your question to members of the
          community.
        </li>
        <li>Review your question and post it to the site.</li>
      </ul>
    </DecsGoodQuestionWrapper>
  );
};

const GoodQuestionGuideWrapper = styled.div`
  width: 100%;
  border: 1px solid #e3e6e8;
  border-radius: 3px;
  box-shadow: 0 1px 2px #0000000d, 0 1px 4px #0000000d, 0 2px 8px #0000000d;
  margin: 10px 0px;
  h2 {
    color: #232629;
    font-size: 15px;
    font-weight: 500;
    padding: 10px 0px 0px 10px;
  }
  .guideTitle {
    background-color: #f8f9f9;
    border-bottom: 1px solid #e3e6e8;
  }
  @media screen and (min-width: 1101px) {
    width: 290px;
  }
`;
const RowBox = styled.div`
  display: flex;
  flex-direction: row;
  padding: 15px 0px;
  svg {
    margin-left: 20px;
  }
`;
const ColumnBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  margin: 0px 10px;
  p {
    margin-bottom: 8px;
    font-size: 12px;
  }
`;
const GoodQuestionGuide = ({ children, title }) => {
  return (
    <GoodQuestionGuideWrapper>
      <div className="guideTitle">
        <h2>{title}</h2>
      </div>
      <RowBox>
        <div>
          <svg
            aria-hidden="true"
            className="spotPencil"
            width="48"
            height="48"
            viewBox="0 0 48 48"
          >
            <path
              opacity=".2"
              d="M31.52 5.2a.34.34 0 0 0-.46.08L7 39.94a.34.34 0 0 0-.06.16l-.54 5.21c-.03.26.24.45.48.34l4.77-2.29c.05-.02.1-.06.13-.1L35.83 8.58a.34.34 0 0 0-.09-.47l-4.22-2.93Z"
            ></path>
            <path d="M28.53 2.82c.4-.58 1.2-.73 1.79-.32l4.22 2.92c.58.4.72 1.2.32 1.79L10.82 41.87c-.13.18-.3.33-.5.43l-4.77 2.28c-.9.44-1.93-.29-1.83-1.29l.55-5.2c.02-.22.1-.43.22-.6L28.53 2.81Zm4.43 3.81L29.74 4.4 28.2 6.6l3.22 2.24 1.53-2.21Zm-2.6 3.76-3.23-2.24-20.32 29.3 3.22 2.24 20.32-29.3ZM5.7 42.4 8.62 41l-2.57-1.78-.34 3.18Zm35.12.3a1 1 0 1 0-.9-1.78 35 35 0 0 1-7.94 3.06c-1.93.43-3.8.3-5.71-.04-.97-.17-1.93-.4-2.92-.64l-.3-.07c-.9-.21-1.81-.43-2.74-.62-2.9-.58-6.6-.49-9.43.65a1 1 0 0 0 .74 1.86c2.4-.96 5.68-1.07 8.3-.55.88.18 1.77.4 2.66.6l.3.08c1 .24 2 .48 3.03.66 2.07.37 4.22.53 6.5.02 3-.67 5.77-1.9 8.41-3.22Z"></path>
          </svg>
        </div>
        <ColumnBox>{children}</ColumnBox>
      </RowBox>
    </GoodQuestionGuideWrapper>
  );
};

const InputWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding: 20px;
  border: 1px solid #e3e6e8;
  border-radius: 3px;
  label.inputTitle {
    color: black;
    font-size: 15px;
    font-weight: 700;
  }
  label.inputDesc {
    color: #232629;
    font-size: 12px;
    margin: 5px 0px;
  }
  input::placeholder {
    color: #babfc4;
  }
`;
const InputContainer = ({ title, desc, children }) => {
  return (
    <InputWrapper>
      <label className="inputTitle">{title}</label>
      <label className="inputDesc">{desc}</label>
      {children}
      <div style={{ margin: '10px 0px 0px' }}>
        <ButtonBlue width="60px">Next</ButtonBlue>
      </div>
    </InputWrapper>
  );
};

const StyledInputContainer = styled.form`
  box-sizing: border-box;
  padding: 0 8px;
  position: relative;
  left: -10px;
`;
const StyledInput = styled.input`
  box-sizing: border-box;
  width: ${(props) => props.width || '756px'};
  height: ${(props) => props.height || '30px'};

  border: 1px solid #e3e6e8;
  border-radius: 3px;
  padding: 10px;

  &:focus {
    outline: none;
    border: 1px solid #6bbbf7;
    box-shadow: 0px 0px 0px 4px #d8e5f2;
  }
`;
const StyledInputComponent = ({ width, height, placeholder }) => {
  return (
    <StyledInputContainer>
      <StyledInput
        width={width}
        height={height}
        type="text"
        placeholder={placeholder}
      ></StyledInput>
    </StyledInputContainer>
  );
};

const AskWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  a {
    text-decoration: none;
    color: #0074cc;
    &:hover {
      color: #379fef;
    }
  }
  button {
    font-weight: 500;
    cursor: pointer;
  }
`;
const BtnContainer = styled.div`
  width: 100%;
  margin: 15px 0px;
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: flex-start;
`;
const ResponsiveContainer = styled.div`
  @media screen and(min-width: 1101px) {
  }
`;
const AskQuestionPage = () => {
  return (
    <AskWrapper>
      <AskPageHeader />
      <DecsGoodQuestion />
      <ResponsiveContainer>
        <GoodQuestionGuide title="Writing a good title">
          <p>Your title should summarize the problem.</p>
          <p>
            You might find that you have a better idea of your title after
            writing out the rest of the question.
          </p>
        </GoodQuestionGuide>
        <InputContainer
          title="Title"
          desc="Be specific and imagine you’re asking a question to another person."
        >
          <StyledInputComponent
            width="102%"
            placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
          />
        </InputContainer>
      </ResponsiveContainer>
      <GoodQuestionGuide title="Introduce the problem">
        <p>
          Explain how you encountered the problem you’re trying to solve, and
          any difficulties that have prevented you from solving it yourself.
        </p>
      </GoodQuestionGuide>
      <InputContainer
        title="What are the details of your problem?"
        desc="Introduce the problem and expand on what you put in the title. Minimum 20 characters."
      >
        {/* 여기에 토스트 UI */}
        <textarea></textarea>
      </InputContainer>
      <GoodQuestionGuide title="Expand on the problem">
        <p>
          Show what you’ve tried, tell us what happened, and why it didn’t meet
          your needs.
        </p>
        <p>
          Not all questions benefit from including code, but if your problem is
          better understood with code you’ve written, you should include a{' '}
          <a href="https://stackoverflow.com/help/minimal-reproducible-example">
            minimal, reproducible example
          </a>
          .
        </p>
        <p>
          Please make sure to post code and errors as text directly to the
          question (and{' '}
          <a href="https://meta.stackoverflow.com/questions/285551/why-should-i-not-upload-images-of-code-data-errors-when-asking-a-question">
            not as images
          </a>
          ),{' '}
          <a href="https://stackoverflow.com/help/formatting">
            and format them appropriately
          </a>
          .
        </p>
      </GoodQuestionGuide>
      <InputContainer
        title="What did you try and what were you expecting?"
        desc="Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters."
      >
        {/* 여기에 토스트 UI */}
        <textarea></textarea>
      </InputContainer>
      <GoodQuestionGuide title="Adding tags">
        <p>
          Tags help ensure that your question will get attention from the right
          people.
        </p>
        <p>
          Tag things in more than one way so people can find them more easily.
          Add tags for product lines, projects, teams, and the specific
          technologies or languages used.
        </p>
        <p>
          <a href="https://stackoverflow.com/help/tagging">
            Learn more about tagging
          </a>
        </p>
      </GoodQuestionGuide>
      <InputContainer
        title="Tags"
        desc="Add up to 5 tags to describe what your question is about. Start typing to see suggestions."
      >
        <StyledInputComponent
          width="102%"
          placeholder="e.g. (angularjs php jquery)"
        />
      </InputContainer>
      <BtnContainer>
        <ButtonBlue width="150px" fontSize="13px">
          Review your question
        </ButtonBlue>
      </BtnContainer>
    </AskWrapper>
  );
};
export default AskQuestionPage;
