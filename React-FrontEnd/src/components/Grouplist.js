import React, {Component} from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
import InsideGroup from "./InsideGroup";
class Grouplist extends Component {

    static propTypes = {
        message: PropTypes.string.isRequired
    };


    componentWillReceiveProps(newProps) {
        this.setState({groupstate_here: newProps.groups});
    }

    constructor(props) {
        super(props);
        this.state = {
            groupstate_here:[],
            groupname:'',
            insidegroup:false

        };
    }

    deletegroup=(name)=>{
        axios.create({withCredentials:true}).post('http://localhost:8080/groups/deletegroup',
            {
                groupname: name,
            }).then((response) => {
            axios.create({withCredentials: true}).get('http://localhost:8080/groups/listgroups',
                {}).then((response) => {
                this.setState({
                    groupstate_here: response.data
                });
                console.log(response.data);
            }).catch((error) => {
                console.log("got errr while posting data", error);
            });

        }).catch((error) => {
            console.log("got errr while posting data", error);
        });
    };





    clickedgroup=(name)=>{
        this.setState({
            insidegroup:true,
            groupname:name
        });
    };

    render() {
        return (
            <div className="form-group">
                {this.state.insidegroup ? <InsideGroup groupname={this.state.groupname}/> :

                    <table className = "table table-hover">
                        <thead>
                        <tr>
                            <th>Group Name</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.groupstate_here.map(tile =>
                            (
                                <tr>
                                    <td className={'tablefield'}>
                                        <div className="form-group">
                                            <label
                                                onClick={() => this.clickedgroup(tile.groupname)}>{tile.groupname}</label>
                                        </div>
                                    </td>
                                    <td className={'tablefield'}>
                                        <button className="btn btn-primary" onClick={() => this.deletegroup(tile.groupname)}>Remove</button>
                                    </td>


                                </tr>
                            )
                        )}

                        </tbody>
                    </table>

                }
            </div>
        );
    }
}

export default Grouplist;