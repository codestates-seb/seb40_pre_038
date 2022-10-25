import styled from 'styled-components';
import { Button } from '../../../components/Buttons';
import { Headline } from '../../../components/Headline';

const PageHeader = () => {
  return (
    <PageHeaderWrapper>
      <Headline>Top Questions</Headline>
      <ButtonContainer>
        <Button type="primary">Ask Question</Button>
      </ButtonContainer>
    </PageHeaderWrapper>
  );
};

export default PageHeader;

const PageHeaderWrapper = styled.div`
  display: flex !important;
`;

const ButtonContainer = styled.div`
  margin-left: 12px !important;
`;
