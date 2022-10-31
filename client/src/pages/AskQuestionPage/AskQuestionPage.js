import styled from 'styled-components';
import AskPageHeader from './AskPageHeader';
import { ButtonBlue } from '../../components/Buttons';
import { InputContainer, StyledInputComponent } from './InputContainer';
import DecsGoodQuestion from './DecsGoodQuestion';
import GoodQuestionGuide from './GoodQuestionGuide';
import useInput from '../../util/useInput';
import axios from 'axios';

const AskWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0px 24px;
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
const Container = styled.div`
  display: flex;
  justify-content: center;
`;
const ContentWrapper = styled.div`
  display: flex;
  flex-direction: column;
`;
const DescWrapper = styled.div`
  @media screen and (min-width: 1101px) {
    display: flex;
    justify-content: center;
  }
`;
const ResponsiveContainer = styled.div`
  width: 100%;
  @media screen and (min-width: 1101px) {
    display: flex;
    flex-direction: row-reverse;
    justify-content: center;
    align-items: flex-start;
  }
`;
const BtnContainer = styled.div`
  width: 100%;
  margin: 15px 0px;
  display: flex;
  flex-direction: row;
  justify-content: left;
  align-items: flex-start;
  @media screen and (min-width: 1101px) {
    max-width: 1150px;
  }
`;
const AskQuestionPage = () => {
  const [titleValue, titleBind, titleReset] = useInput('');
  const [problemValue, problemBind, problemReset] = useInput('');
  const [expectValue, expectBind, expectReset] = useInput('');
  const [tagsValue, tagsBind, tagsReset] = useInput('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const postBody = JSON.stringify({
      memberId: 2,
      title: titleValue,
      body: problemValue + ' ' + expectValue,
      tagBody: tagsValue,
    });
    console.log('post:', postBody);
    axios
      .post('http://localhost:8080/questions/add', postBody)
      .then(function (response) {
        console.log(response);
        console.log(postBody);
      })
      .catch(function (error) {
        console.log(postBody);
        console.log(error);
      });
    titleReset();
    problemReset();
    expectReset();
    tagsReset();
  };

  return (
    <AskWrapper>
      <Container>
        <AskPageHeader />
      </Container>
      <ContentWrapper>
        <DescWrapper>
          <DecsGoodQuestion />
        </DescWrapper>
        <form onSubmit={handleSubmit}>
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
                value={titleBind}
                placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
              />
            </InputContainer>
          </ResponsiveContainer>
          <ResponsiveContainer>
            <GoodQuestionGuide title="Introduce the problem">
              <p>
                Explain how you encountered the problem you’re trying to solve,
                and any difficulties that have prevented you from solving it
                yourself.
              </p>
            </GoodQuestionGuide>
            <InputContainer
              title="What are the details of your problem?"
              desc="Introduce the problem and expand on what you put in the title. Minimum 20 characters."
            >
              {/* 여기에 토스트 UI */}
              <textarea
                value={problemValue}
                onChange={problemBind.onChange}
                disabled={titleValue === '' ? true : false}
              ></textarea>
            </InputContainer>
          </ResponsiveContainer>
          <ResponsiveContainer>
            <GoodQuestionGuide title="Expand on the problem">
              <p>
                Show what you’ve tried, tell us what happened, and why it didn’t
                meet your needs.
              </p>
              <p>
                Not all questions benefit from including code, but if your
                problem is better understood with code you’ve written, you
                should include a{' '}
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
              <textarea
                value={expectValue}
                onChange={expectBind.onChange}
                disabled={problemValue === '' ? true : false}
              ></textarea>
            </InputContainer>
          </ResponsiveContainer>
          <ResponsiveContainer>
            <GoodQuestionGuide title="Adding tags">
              <p>
                Tags help ensure that your question will get attention from the
                right people.
              </p>
              <p>
                Tag things in more than one way so people can find them more
                easily. Add tags for product lines, projects, teams, and the
                specific technologies or languages used.
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
                value={tagsBind}
                placeholder="e.g. (angularjs php jquery)"
                disabled={expectValue === '' ? true : false}
              />
            </InputContainer>
          </ResponsiveContainer>
          <Container>
            <BtnContainer>
              <ButtonBlue width="150px" fontSize="13px">
                Review your question
              </ButtonBlue>
            </BtnContainer>
          </Container>
        </form>
      </ContentWrapper>
    </AskWrapper>
  );
};
export default AskQuestionPage;
