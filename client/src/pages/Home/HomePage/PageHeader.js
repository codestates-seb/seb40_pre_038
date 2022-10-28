import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { Button } from '../../../components/Buttons';
import { Headline } from '../../../components/Headline';

const PageHeaderWrapper = styled.div`
  display: flex !important;
`;

const ButtonContainer = styled.div`
  margin-left: 12px !important;
`;

const PageHeader = () => {
  return (
    <PageHeaderWrapper>
      <Headline>Top Questions</Headline>
      <ButtonContainer>
        <Link to="/questions/ask">
          <Button type="primary" whiteSpace="nowrap">
            Ask Question
          </Button>
        </Link>
      </ButtonContainer>
    </PageHeaderWrapper>
  );
};

export default PageHeader;
