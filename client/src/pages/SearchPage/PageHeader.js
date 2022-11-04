import { useNavigate, Link } from 'react-router-dom';
import styled from 'styled-components';
import { Button } from '../../components/Buttons';
import { Headline } from '../../components/Headline';
import SearchTips from './SearchTips';
import SortTabs from './SortTabs';

const HeadlinkWrapper = styled.div`
  display: flex !important;
  margin-bottom: 24px !important;
  align-items: flex-start !important;
  justify-content: space-between !important;
  flex-wrap: wrap !important;
`;
const HeadLineButtonWrapper = styled.div`
  display: flex !important;
  margin-left: 32px !important;
  flex: 1 auto !important;
  align-items: center !important;
  justify-content: flex-end !important;
`;

const SearchTipLink = styled(Link)`
  margin-right: 20px !important;
`;

const SubWrapper = styled.div`
  line-height: 1 !important;
  color: #6a737c !important;
  font-size: 12px !important;
  margin-bottom: 24px !important;

  div:first-child {
    margin-bottom: 8px !important;
  }
`;

const BoldSpan = styled.span`
  font-weight: 600 !important;
`;

const PageHeader = () => {
  const navigate = useNavigate();

  const handleClickAskQuestionBtn = () => {
    navigate('/questions/ask');
  };

  return (
    <>
      <HeadlinkWrapper>
        <Headline className="mb0">Search Results</Headline>
        <HeadLineButtonWrapper>
          <SearchTipLink to="#">Advanced Search Tips</SearchTipLink>
          <Button
            type="primary"
            whiteSpace="nowrap"
            onClick={handleClickAskQuestionBtn}
          >
            Ask Question
          </Button>
        </HeadLineButtonWrapper>
      </HeadlinkWrapper>
      <SearchTips />
      <SubWrapper>
        <div>Results for why javascript</div>
        <div>
          Search options <BoldSpan>not deleted</BoldSpan>
        </div>
      </SubWrapper>
      <SortTabs />
    </>
  );
};

export default PageHeader;
