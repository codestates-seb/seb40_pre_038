import SummaryStats from './SummaryStats';
import SummaryContent from './SummaryContent';
import styled from 'styled-components';

const QuestionMiniList = () => {
  return (
    <QuestionSummary
      id="question-summary-74189299"
      className="s-post-summary    js-post-summary"
      data-post-id={74189299}
      data-post-type-id={1}
    >
      <SummaryStats />
      <SummaryContent />
    </QuestionSummary>
  );
};

export default QuestionMiniList;

const QuestionSummary = styled.div``;
