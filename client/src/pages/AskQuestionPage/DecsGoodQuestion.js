import styled from 'styled-components';

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
  @media screen and (min-width: 1101px) {
    margin-right: 340px;
    max-width: 800px;
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

export default DecsGoodQuestion;
