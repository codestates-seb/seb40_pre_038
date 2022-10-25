import styled from "styled-components";

const SearchbarContainer = styled.div`
    padding: 0 8px;
`

const SearchbarInput = styled.input`
    width: ${(props) => props.width || "756px"};
    height: ${(props) => props.height || "30px"};

    border: 1px solid #BABFC4;
    border-radius: 3px;

    &:focus {
        outline: none;
        border: 1px solid #6BBBF7;
        box-shadow: 0px 0px 0px 4px #d8e5f2
    }
`

const Searchbar = ({ width, height }) => {
    return (
        <SearchbarContainer>
            <SearchbarInput width={width} height={height}></SearchbarInput>
        </SearchbarContainer>
    )
}   

export default Searchbar;