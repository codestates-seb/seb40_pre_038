import styled from 'styled-components';
import QuestionMiniList from './QuestionMiniList';

const QlistWrapper = styled.div`
  clear: both;
  margin-left: calc(12px * -1);
  margin-right: calc(12px * -1);
  width: calc(100% + 2 * 12px);
  border-top: 1px solid #d6d9dc;

  &.questions {
    width: auto;
    float: none;
    margin-bottom: 20px;
  }

  @media screen and (max-width: 980px) {
    margin-left: calc(16px * -1);
    margin-right: calc(16px * -1);
    width: calc(100% + 2px * 16px);
  }
`;

const QuestionMiniLists = styled.div`
  &.question-mini-list {
    margin-bottom: 30px;
  }
`;

const QuestionListWrapper = ({ className, questionsList }) => {
  return (
    <QlistWrapper className={className}>
      <QuestionMiniLists className={className}>
        {questionsList?.map((question) => (
          <QuestionMiniList
            key={question.questionId}
            className={className}
            question={question}
          />
        ))}
      </QuestionMiniLists>
    </QlistWrapper>
  );
};

export default QuestionListWrapper;
