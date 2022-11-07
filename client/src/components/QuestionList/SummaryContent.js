import { Link } from 'react-router-dom';
import styled from 'styled-components';
import SMetaTags from './SMetaTags';
import SUserCard from './SUserCard';

const SummaryContentWrapper = styled.div`
  flex-grow: 1;
  max-width: 100%;
`;

const SummaryContentTitle = styled.h3`
  font-weight: 400;
  display: block;
  font-size: 1.30769231rem;
  margin-top: -0.15rem;
  margin-bottom: 0.3846rem;
  padding-right: 24px;
  line-height: calc((13 + 4) / 13);
  word-break: break-word !important;
  overflow-wrap: break-word !important;
  hyphens: auto !important;
`;

const SummaryContentTitleLink = styled(Link)`
  word-break: break-word !important;
  overflow-wrap: break-word !important;
  -webkit-hyphens: auto !important;
  -moz-hyphens: auto !important;
  -ms-hyphens: auto !important;
  hyphens: auto !important;
  color: #0074cc;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI Adjusted',
    'Segoe UI', 'Liberation Sans', sans-serif;
  text-decoration: none;
  cursor: pointer;
  user-select: auto;

  :visited {
    color: #0063bf;
  }
  :hover,
  :active {
    color: #0a95ff;
  }
`;

const SummaryContentExcerpt = styled.div`
  margin-top: calc(2px * -1);
  margin-bottom: 8px;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI Adjusted',
    'Segoe UI', 'Liberation Sans', sans-serif;
  color: #3b4045;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  word-break: break-word !important;
  overflow-wrap: break-word !important;
  hyphens: auto !important;
`;

const SummaryMeta = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  column-gap: 6px;
  row-gap: 8px;
`;

const SummaryContent = ({ className, question }) => {
  const { title, expect, questionId } = question;
  return (
    <SummaryContentWrapper>
      <SummaryContentTitle>
        <SummaryContentTitleLink to={`/questions/${questionId}`}>
          {title}
        </SummaryContentTitleLink>
      </SummaryContentTitle>
      {className !== 'questions' ? null : (
        <SummaryContentExcerpt>{expect}</SummaryContentExcerpt>
      )}
      <SummaryMeta>
        <SMetaTags question={question} />
        <SUserCard question={question} />
      </SummaryMeta>
    </SummaryContentWrapper>
  );
};

export default SummaryContent;
