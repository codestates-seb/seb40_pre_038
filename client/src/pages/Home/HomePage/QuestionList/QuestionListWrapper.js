import styled from 'styled-components';
import QuestionMiniList from './QuestionMiniList';

const QlistWrapper = styled.div`
  clear: both;
  margin-left: calc(12px * -1);
  margin-right: calc(12px * -1);
  width: calc(100% + 2 * 12px);
  border-top: 1px solid #d6d9dc;

  @media screen and (max-width: 980px) {
    margin-left: calc(16px * -1);
    margin-right: calc(16px * -1);
    width: calc(100% + 2px * 16px);
  }
`;

const QuestionMiniLists = styled.div`
  margin-bottom: 30px;
`;

const QuestionListWrapper = () => {
  return (
    <QlistWrapper>
      <QuestionMiniLists>
        <div>
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
          <QuestionMiniList />
        </div>
      </QuestionMiniLists>
    </QlistWrapper>
  );
};

export default QuestionListWrapper;
